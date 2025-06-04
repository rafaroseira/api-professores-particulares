package com.example.api.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private float pricePerHour;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(length = 40, nullable = false)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modality modality;

    @Column(length = 11, nullable = false)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
        name = "ad_languages",
        joinColumns = @JoinColumn(name = "ad_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages;

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Ad(float pricePerHour, String title, String description, String city, Modality modality,
            String phoneNumber, List<Language> languages) {
        this.pricePerHour = pricePerHour;
        this.title = title;
        this.description = description;
        this.city = city;
        this.modality = modality;
        this.phoneNumber = phoneNumber;
        this.languages = languages;
    }

    public Ad() {
    }

}