package com.example.ecf3.exception;

public class SamePlayerException extends Exception{
    public SamePlayerException() {
        super("Même joueur choisi");
    }
}
