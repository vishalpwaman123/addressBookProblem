package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Test;

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

}
