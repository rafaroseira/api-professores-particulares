package com.example.api.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(){super("Email já registrado");}
}
