package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserInfoDTO(
    @NotBlank @Size(max = 30) String name, 
    @NotBlank @Size(max = 30) String lastName
) {

}
