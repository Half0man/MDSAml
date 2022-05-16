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
import java.util.List;
@Controller
public class AppController {


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
                        transactionRepo.save(TransactionFiles.get(i));
                        i++;
                        }while(TransactionFiles.get(i)!=null);



            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
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
        //long identification = Long.parseLong(id);
        clientRepo.deleteById(id);
        return "redirect:/users";
    }
}
