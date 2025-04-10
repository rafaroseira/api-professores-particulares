package com.example.api.exceptions;

public class CantCreateAdException extends RuntimeException{
    public CantCreateAdException(String msg){super(msg);}
    public CantCreateAdException(){super();}
}
