package com.bridgelabz.addressbook;

public class addressBookException extends Throwable {

    public enum ExceptionType {
        CANNOT_CREATE_FILE,ENTERED_EMPTY,NO_FILE_FOUND;
    }
    ExceptionType type;

    public addressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
