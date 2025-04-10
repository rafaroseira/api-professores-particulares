package com.example.api.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.api.model.Language;
import com.example.api.repository.LanguageRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner{

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public void run(String... args) throws Exception{

        List<Language> languages = new ArrayList<Language>();

        languages.add(new Language("Inglês"));
        languages.add(new Language("Espanhol"));
        languages.add(new Language("Italiano"));
        languages.add(new Language("Francês"));
        languages.add(new Language("Alemão"));
        languages.add(new Language("Japonês"));
        languages.add(new Language("Mandarim"));
        languages.add(new Language("Russo"));
        languages.add(new Language("Polonês"));
        languages.add(new Language("Grego"));
        languages.add(new Language("Árabe"));

        languageRepository.saveAll(languages);
    }
}
