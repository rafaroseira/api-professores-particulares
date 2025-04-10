package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Language;

public interface LanguageRepository extends JpaRepository<Language,Byte>{

    Language findByName(String name);
}
