package com.example.api.exceptions;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException(){super("O anúncio não existe!");}
    public AdNotFoundException(String msg){super(msg);}
}