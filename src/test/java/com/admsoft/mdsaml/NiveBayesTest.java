package com.admsoft.mdsaml;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class NiveBayesTest {
    String filepath="C:\\Users\\lambo\\Documents\\Praca Inżynierska\\test3.csv";
    String modelFilepath="C:\\Users\\lambo\\Documents\\Praca Inżynierska\\plik.dat";
    String testRecord[]={"karta","10000-100000","04 do 06","czlowiek m18-25","100-500"};
    @Test
    void testbayes(){
        BayesModelHandler bayesModelHandler=new BayesModelHandler();
        NaiveBeysHelper naiveBeysHelper =bayesModelHandler.readBayes(modelFilepath);
        List<String[]> items= (List<String[]>) naiveBeysHelper.loadItems(filepath);
        float numberCorrect=0;
        float totalNumber=0;
        String record[]= new String[5];
        String result;
        for (int i = 0; i <items.size() ; i++) {
            for (int j = 0; j <5 ; j++) {
                record[j]=items.get(i)[j];
            }
            result=naiveBeysHelper.bayes.classify(Arrays.asList(record)).getCategory();
            if (result.equals(items.get(i)[5])){
                numberCorrect++;
            }
            totalNumber++;
        }

        float accuracy=numberCorrect/totalNumber;
        System.out.println(accuracy);

    }



}
