package com.example.api.dto;

public interface AdPreviewDTO {
    Integer getId();
    String getTitle();
    Float getPricePerHour();
    String getModality();
    String getName();
    String getLastName();
    Float getRating(); //won't work with primitive float
    String getImage();
}