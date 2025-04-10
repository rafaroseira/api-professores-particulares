package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordDTO(
    @NotBlank String currentPassword, 
    @NotBlank @Size(max = 30) String newPassword) {
}
