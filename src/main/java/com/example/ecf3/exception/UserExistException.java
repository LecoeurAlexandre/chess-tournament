package com.example.ecf3.exception;

public class UserExistException extends Exception{
    public UserExistException() {
        super("User Exist");
    }
}
