package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testAddressBook {

    addressBookAnalysis addressBookAnalyser = new addressBookAnalysis();

    @Test
    public void givenFileNametoCreate_whenNotExist_shouldCreateFileAndReturnTrue() {
        try {

            Assert.assertEquals(true, addressBookAnalyser.createFile("MyAddress.json"));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileNametoCreate_whenExists_shouldNotCreateFileAndReturnFalse() {
        try {

            Assert.assertEquals(false, addressBookAnalyser.createFile("MyAddress.json"));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFile_whenAddPersonDetails_shouldReturnPersonMobileNumber() {
        PersonDetails personDetails=new PersonDetails("Vishal", "Wamankar", "Kondhwa", "Pune", "Maharashtra", "411048", "7758039722");
        Assert.assertEquals("7758039722", addressBookAnalyser.addPersonDetailsInFile(personDetails).iterator().next().getPhoneNumber());
    }

    @Test
    public void givenFile_whenAddPersonDetails_shouldReturnFullName() {
        PersonDetails personDetails=new PersonDetails("Vishal", "Wamankar", "Kondhwa", "Pune", "Maharashtra", "411048", "7758039722");
        Assert.assertEquals("Vishal Wamankar", addressBookAnalyser.addPersonDetailsInFile(personDetails).iterator().next().getFullName());
    }

    @Test
    public void givenFileName_whenSavePersonDetails_shouldWriteIntoJson() {
        try {
            PersonDetails personDetails = new PersonDetails("Rahul", "Wamankar", "Kondhwa", "Pune", "Maharashtra", "411048", "7758039722");
            Assert.assertEquals(true, addressBookAnalyser.save("MyAddress.json",addressBookAnalyser.addPersonDetailsInFile(personDetails)));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenReadPersonDetails_shouldReadPersonDetailsFromJson(){
        try {
            List<PersonDetails> list =addressBookAnalyser.readPersonInfo("MyAddress.json");
            Assert.assertEquals(true,addressBookAnalyser.checksizeofList(list));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {

            addressBookAnalyser.readPersonInfo("WrongAddress.json");
        } catch (addressBookException e) {
            Assert.assertEquals(addressBookException.ExceptionType.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenEmptyFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            addressBookAnalyser.readPersonInfo("");
        } catch (addressBookException e) {
            Assert.assertEquals(addressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenNullFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            addressBookAnalyser.readPersonInfo(null);
        } catch (addressBookException e) {
            Assert.assertEquals(addressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenFileName_whenDeletedPersonDetails_shouldDeletePersonandReturnTrue() {
        try {
            Assert.assertEquals(true,addressBookAnalyser.deletingPersonDetails("MyAddress.json","Rahul"));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenEditPersonDetails_shouldReturnUpdatedDetails() {
        try {
            PersonDetails personDetails = new PersonDetails("Vishal", "Wamankar", "Jalgaon by pass", "Jalgaon", "Maharashtra", "411048", "8806787166");
            Assert.assertEquals(true, addressBookAnalyser.editingPersonDetails("7758039722", "MyAddress.json", personDetails));
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenSortPersonDetailsByName_shouldReturnSortedNameAtFirst() {
        try {
            ArrayList<PersonDetails> sortedData=addressBookAnalyser.getFieldWiseSortedData(SortedByField.Parameter.FIRST_NAME,"MyAddress.json");
            Assert.assertEquals("Akash",sortedData.get(0).FirstName);
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenSortPersonDetailsByName_shouldReturnSortedNameAtLast() {
        try {
            ArrayList<PersonDetails> sortedData=addressBookAnalyser.getFieldWiseSortedData(SortedByField.Parameter.FIRST_NAME,"MyAddress.json");
            Assert.assertEquals("Vishal",sortedData.get(sortedData.size()-1).FirstName);
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenSortPersonDetailsByZip_shouldReturnSortedNameAtFirst() {
        try {
            ArrayList<PersonDetails> sortedData=addressBookAnalyser.getFieldWiseSortedData(SortedByField.Parameter.ZIP,"MyAddress.json");
            Assert.assertEquals("Amit",sortedData.get(0).FirstName);
        } catch (addressBookException e) {
            e.printStackTrace();
        }
    }

}
