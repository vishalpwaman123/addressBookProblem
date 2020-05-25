package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        return personInformation;
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

    public ArrayList<PersonDetails> readPersonInfo(String fileName) throws addressBookException {
        try {
            if (fileName.length() == 0)
                throw new addressBookException("File Name Cannot be empty", addressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                Gson gson = new Gson();
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(file));
                PersonDetails[] personDetails = gson.fromJson(br, PersonDetails[].class);
                for (int i = 0; i < personDetails.length; i++) {
                    personInformation.add(personDetails[i]);
                }
            }

        } catch (NullPointerException e) {
            throw new addressBookException("File Name Cannot be Null", addressBookException.ExceptionType.ENTERED_NULL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personInformation;
    }


    public boolean checksizeofList(List<PersonDetails> list) {
        if (list.size() != 0)
            return true;
        return false;
    }

    public boolean deletingPersonDetails(String fileName, String firstName) throws addressBookException {
        try {
            if (fileName.length() == 0)
                throw new addressBookException("File Name Cannot be empty", addressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            List<PersonDetails> personDetailsList = readPersonInfo(fileName);
            for (PersonDetails personDetails1 : personDetailsList) {
                if (personDetails1.getFirstName().equals(firstName)) {
                    personDetailsList.remove(personDetails1);
                    Gson gson = new Gson();
                    String json = gson.toJson(personDetailsList);
                    FileWriter writer = new FileWriter(file);
                    writer.write(json);
                    writer.close();
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editingPersonDetails(String phoneNumber, String fileName, PersonDetails personDetails) throws addressBookException {
        List<PersonDetails> personList = readPersonInfo(fileName);
        File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        try {
            for (PersonDetails person : personList) {
                if (person.getPhoneNumber().equals(phoneNumber)) {
                    person.AssignFirstName(personDetails.getFirstName());
                    person.AssignLastName(personDetails.getLastName());
                    person.AssignAddress(personDetails.getAddress());
                    person.AssignCity(personDetails.getCity());
                    person.AssignState(personDetails.getState());
                    person.AssignZip(personDetails.getZip());
                    person.AssignPhoneNumber(personDetails.getPhoneNumber());
                    Gson gson = new Gson();
                    String json = gson.toJson(personList);
                    FileWriter writer = null;
                    writer = new FileWriter(file);
                    writer.write(json);
                    writer.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList getFieldWiseSortedData(SortedByField.Parameter parameter, String fileName) throws addressBookException {
        List<PersonDetails> personList = readPersonInfo(fileName);
        Comparator<PersonDetails> personDetailsComparator;
        if (personList == null || personList.size() == 0) {
            throw new addressBookException("No Data Found", addressBookException.ExceptionType.NO_FILE_FOUND);
        }
        personDetailsComparator = SortedByField.getParameter(parameter);
        ArrayList sortedData = personList.stream()
                .sorted(personDetailsComparator)
                .collect(Collectors.toCollection(ArrayList::new));
        this.save(fileName, sortedData);
        return sortedData;
    }

    public void printPersonDetails(String fileName) throws addressBookException {
        try {
            if (fileName.length() == 0)
                throw new addressBookException("File Name Cannot be empty", addressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                Gson gson = new Gson();
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(file));
                PersonDetails[] personDetails = gson.fromJson(br, PersonDetails[].class);
                System.out.println(Arrays.toString(personDetails));
                for (int i = 0; i < personDetails.length; i++) {
                    personInformation.add(personDetails[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveAs(String fileName, ArrayList<PersonDetails> personDetails) throws addressBookException {
        try {
            if (fileName.length() == 0)
                throw new addressBookException("File Name Cannot be empty", addressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                System.out.println("Please give other file Name");
                return false;
            }
            this.createFile(fileName);
            Gson gson = new Gson();
            String json = gson.toJson(personDetails);
            FileWriter writer = null;
            writer = new FileWriter("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}