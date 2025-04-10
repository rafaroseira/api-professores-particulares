package com.example.api.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){super("Usuário não existe!");}
}
