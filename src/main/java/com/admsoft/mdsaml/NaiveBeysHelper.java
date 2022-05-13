package com.admsoft.mdsaml;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NaiveBeysHelper {
    Classifier<String,String>bayes =new BayesClassifier<String,String>();

    List<String[]> loadItems(String fileName){
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> transactions= reader.readAll();
            return transactions;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        List<String[]>nul=new ArrayList<String[]>();
        return nul;

    }
    public void learn(List<String[]>transactions){
        bayes.setMemoryCapacity(100000000);
        String tmp[]=new String[5];
        for (int i = 1; i < transactions.size(); i++) {
            for (int j = 0; j <5 ; j++) {
                tmp[j]=transactions.get(i)[j];
            }
            if(transactions.get(i)[5].equals("true"))
                bayes.learn("true", Arrays.asList(tmp));

            else
                bayes.learn("false", Arrays.asList(tmp));
        }

    }
}
