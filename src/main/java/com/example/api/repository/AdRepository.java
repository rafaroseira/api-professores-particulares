package com.example.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import com.example.api.dto.AdDTO;
import com.example.api.dto.AdPreviewDTO;
import com.example.api.model.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    /*
     * Easiest way to map query results to DTO's is to use projections
     * Uses interfaces
     * It's slower than using @SqlResultMapping, though
     * Define aliases in the SELECT statement to properly work
     * fonte: StackOverflow
     */
    @NativeQuery(value = "SELECT a.id AS id, a.title AS title, a.price_per_hour AS pricePerHour, a.modality AS modality, u.name AS name, u.last_name AS lastName, AVG(rating) AS rating FROM ads AS a JOIN users AS u ON a.id = u.ad_id JOIN ad_languages AS al ON a.id = al.ad_id JOIN languages AS l ON al.language_id = l.id LEFT JOIN teachers_feedbacks AS tf ON tf.teacher_id = u.id WHERE a.city = ?1 AND l.name = ?2 GROUP BY u.id")
    List<AdPreviewDTO> findAdsPreviews(String city, String language);

    @NativeQuery(value = "SELECT u.name AS name, u.last_name AS lastName, a.price_per_hour AS pricePerHour, a.title AS title, a.description AS description, a.city AS city, a.modality AS modality, a.phone_number AS phoneNumber FROM ads AS a JOIN users AS u ON a.id = u.ad_id WHERE a.id = ?1")
    AdDTO findAd(int id);
}