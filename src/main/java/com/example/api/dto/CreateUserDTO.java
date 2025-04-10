package com.example.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO( 
    @NotBlank @Size(max = 30) String name, 
    @NotBlank @Size(max = 30) String lastName, 
    @NotBlank @Email @Size(max = 50) String email, 
    @NotBlank @Size(max = 30) String password, 
    @NotBlank String role) {
}