package com.admsoft.mdsaml;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class UploadController {

    @GetMapping("/")
    public String index() {
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

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }
}