package com.bridgelabz.addressbook;

public class PersonDetails {

    public String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    public String Zip;
    private String PhoneNumber ;
    public String fullName;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public PersonDetails(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber) {
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        City = city;
        State = state;
        Zip = zip;
        PhoneNumber = phoneNumber;
        fullName=FirstName+" "+LastName;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip=" + Zip +
                ", PhoneNumber=" + PhoneNumber +
                '}';
    }


}
