package com.example.api.dto;

import java.util.List;
import com.example.api.model.Modality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateAdDTO( 
    @PositiveOrZero Float pricePerHour, 
    @NotBlank @Size(max = 100) String title, 
    @NotBlank @Size(max = 2000) String description, 
    @NotBlank @Size(max = 40) String city, 
    @NotBlank @Size(min = 11, max = 11) String phoneNumber, 
    @NotNull Modality modality,
    @NotNull List<String> languages) {
}
