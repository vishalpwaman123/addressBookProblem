package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class addressBookAnalysis {

    ArrayList<PersonDetails> personInformation = new ArrayList<PersonDetails>();

    public boolean createFile(String fileName) throws addressBookException {
        try {
            if (fileName.length() == 0)
                throw new addressBookException("File Name Cannot be empty", addressBookException.ExceptionType.ENTERED_EMPTY);
            File files = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            boolean isFileExist = files.exists();
            if (isFileExist) {
                return false;
            }
            files.createNewFile();
            return true;
        } catch (IOException e) {
            throw new addressBookException("Cannot Create File in path", addressBookException.ExceptionType.CANNOT_CREATE_FILE);
        }
    }

    public ArrayList<PersonDetails> addPersonDetailsInFile(PersonDetails personDetails) {
        personInformation.add(personDetails);
        return  personInformation;
    }


    public boolean save(String fileName, ArrayList<PersonDetails> personDetails) throws addressBookException {
        try {
            if (fileName.length() == 0)
                throw new addressBookException("File Name Cannot be empty", addressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                Gson gson = new Gson();
                String json = gson.toJson(personDetails);
                FileWriter writer = null;
                writer = new FileWriter("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
                writer.write(json);
                writer.close();
                return true;
            }

        } catch (IOException e) {
            throw new addressBookException("Cannot Save in the File", addressBookException.ExceptionType.NO_FILE_FOUND);
        } catch (addressBookException e) {
            e.printStackTrace();
        }
        return false;
    }
}