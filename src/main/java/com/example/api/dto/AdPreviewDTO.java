package com.example.api.dto;

public interface AdPreviewDTO {
    Integer getId();
    String getTitle();
    Float getPricePerHour();
    String getModality();
    String getName();
    String getLastName();
    Float getRating(); //não funciona com float primitivo
}