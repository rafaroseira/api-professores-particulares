package com.example.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostFeedbackDTO(
    Integer teacherId, 
    @NotBlank @Size(max = 500) String comment, 
    @Min(value = 1) @Max(value = 5) Byte rating
) {

}
