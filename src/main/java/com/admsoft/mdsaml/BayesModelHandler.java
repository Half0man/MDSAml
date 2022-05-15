package com.admsoft.mdsaml;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

@Getter
@Setter
public class BayesModelHandler {

    public void writeBayes(NaiveBeysHelper naiveBeysHelper, String filepath) {
        try (FileOutputStream fos = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(naiveBeysHelper);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public NaiveBeysHelper readBayes(String filepath) {

        NaiveBeysHelper beysHelper = new NaiveBeysHelper();
        try (FileInputStream fis = new FileInputStream(filepath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            beysHelper = (NaiveBeysHelper) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return beysHelper;
    }
}
