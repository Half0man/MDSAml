package com.admsoft.mdsaml;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;


public class NiveBayesTest {
    String filepath="C:\\Users\\lambo\\Documents\\Praca Inżynierska\\test.csv";
    String modelFilepath="C:\\Users\\lambo\\Documents\\Praca Inżynierska\\plik.dat";
    String testRecord[]={"karta","10000-100000","04 do 06","czlowiek m18-25","100-500"};
    @Test
    void testbayes(){
        BayesModelHandler bayesModelHandler=new BayesModelHandler();
        NaiveBeysHelper naiveBeysHelper =bayesModelHandler.readBayes(modelFilepath);
       System.out.println(naiveBeysHelper.bayes.classify(Arrays.asList(testRecord)));



    }
}
