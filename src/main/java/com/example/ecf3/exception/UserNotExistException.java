package com.example.ecf3.exception;

public class UserNotExistException extends Exception{
    public UserNotExistException() {
        super("User Not Exist");
    }
}
