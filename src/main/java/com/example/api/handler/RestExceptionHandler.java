package com.example.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.api.exceptions.AdNotFoundException;
import com.example.api.exceptions.CantCreateAdException;
import com.example.api.exceptions.EmailAlreadyExistsException;
import com.example.api.exceptions.LanguageNotFoundException;
import com.example.api.exceptions.RoleException;
import com.example.api.exceptions.StorageException;
import com.example.api.exceptions.StorageFileNotFoundException;
import com.example.api.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({
        RoleException.class,
        EmailAlreadyExistsException.class,
        CantCreateAdException.class,
        ConstraintViolationException.class
    })
    private ResponseEntity<String> badRequestHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({
        AdNotFoundException.class,
        UserNotFoundException.class,
        LanguageNotFoundException.class,
        StorageFileNotFoundException.class
    })
    private ResponseEntity<String> notFoundHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<String> badCredentialsHandler(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

        @ExceptionHandler(StorageException.class)
    private ResponseEntity<String> storageHandler(StorageException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}