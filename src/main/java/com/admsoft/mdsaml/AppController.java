package com.admsoft.mdsaml;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
public class AppController {

    String Filepath="C:\\Users\\lambo\\Documents\\Praca Inżynierska\\model.dat";
    BayesModelHandler bayesModelHandler=new BayesModelHandler();
    NaiveBeysHelper beysHelper= bayesModelHandler.readBayes(Filepath);
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TransactionFileRepository transactionRepo;
    @Autowired
    private ClientRepository clientRepo;
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "singup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByEmail(authentication.getName());
        String mail = user.getEmail();
        List<Client> listUsers = clientRepo.findByUser(mail);
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName());

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {


            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {


                CsvToBean<TransactionFile> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(TransactionFile.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();


                List<TransactionFile> TransactionFiles = csvToBean.parse();



                model.addAttribute("TransactionFile", TransactionFiles);
                model.addAttribute("status", true);
                    int i=0;
                    do {
                        TransactionFiles.get(i).setClient(clientRepo.findById(user.getCurrentClientId()));
                        TransactionFiles.get(i).setIsFraud("undicided");
                        transactionRepo.save(TransactionFiles.get(i));
                        i++;
                        }while(TransactionFiles.get(i)!=null);



            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "redirect:TransactionUplad";
    }


    @GetMapping("/showUploadClientForm")
    public String getClientData(Model model){
       model.addAttribute("client",new tmpClient());
       return "client_upload_form";
    }
    @PostMapping("/saveClient")
    public String submitClient(@ModelAttribute tmpClient tmpClient ,Model model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Client client=new Client();
        client.setType(tmpClient.getTmpType());
        client.setName(tmpClient.getTmpName());
        client.setUser(userRepo.findByEmail(authentication.getName()));
        clientRepo.save(client);
        return "redirect:/users";
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String deleteClient(@RequestParam long id){
        //transactionRepo.deleteByClient(id);
        clientRepo.deleteById(id);
        return "redirect:/users";
    }
    @RequestMapping(value = "/addTransactions",method = RequestMethod.POST)
    public String menageClient(@RequestParam long id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName());
        user.setCurrentClientId(id);
        userRepo.save(user);
        return "redirect:/TransactionUplad";
    }
    @GetMapping("/TransactionUplad")
    public String listTransactions(Model model) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName());
        List<TransactionFile> listTransactionfiles = transactionRepo.findByClient(user.getCurrentClientId());
        model.addAttribute("listTransactionfiles", listTransactionfiles);
        return "TransactionUplad";
    }
    @RequestMapping(value = "/deletetransaction",method = RequestMethod.POST)
    public String deleteTransaction(@RequestParam long id){
        transactionRepo.deleteById(id);
        return "redirect:/TransactionUplad";
    }
    @RequestMapping(value = "/checkTransaction",method = RequestMethod.POST)
    public String checkTransaction(@RequestParam long id){
        TransactionFile transactionFile= transactionRepo.findById(id);

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName());
        Client client =clientRepo.findById(user.getCurrentClientId());
        String transaction[]=new String[5];
        transaction[0]=transactionFile.getTypeOfAction();
        CashClasificator cashClasificator=new CashClasificator();
        transaction[1]=cashClasificator.clasifyAmount(transactionFile.getAmountOfMoney());
        transaction[2]=transactionFile.getTransactionTime();
        transaction[3]=client.getType();
        transaction[4]=cashClasificator.clasifyAmountleft(transactionFile.getAmountOnMoneyLeft());
        transactionFile.setIsFraud(beysHelper.bayes.classify(Arrays.asList(transaction)).getCategory());;
        transactionRepo.save(transactionFile);
    return "redirect:/TransactionUplad";
    }
    @RequestMapping(value = "/back",method = RequestMethod.POST)
    public String goBack(){
        return "redirect:/users";
    }
}
