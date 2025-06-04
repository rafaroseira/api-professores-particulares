package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api.dto.AdDTO;
import com.example.api.dto.AdPreviewDTO;
import com.example.api.dto.CreateAdDTO;
import com.example.api.exceptions.AdNotFoundException;
import com.example.api.exceptions.CantCreateAdException;
import com.example.api.exceptions.LanguageNotFoundException;
import com.example.api.exceptions.UserNotFoundException;
import com.example.api.model.Ad;
import com.example.api.model.Language;
import com.example.api.model.User;
import com.example.api.repository.AdRepository;
import com.example.api.repository.LanguageRepository;
import com.example.api.repository.UserRepository;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createAd(CreateAdDTO dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User teacher = userRepository.findByEmail(auth.getName());
        isTeacherValid(teacher);
        canCreateAd(teacher);

        List<Language> languages = new ArrayList<>();

        for(String str : dto.languages()){
            if(isLanguageValid(str)){
                languages.add(languageRepository.findByName(str));
            } else {
                throw new LanguageNotFoundException();
            }
        }

        Ad ad = adRepository.save(new Ad(dto.pricePerHour(), dto.title(), dto.description(), dto.city(),
            dto.modality(), dto.phoneNumber(), languages));

        teacher.setAd(ad);
        userRepository.save(teacher);
    }

    @Transactional
    public List<AdPreviewDTO> preview(String city, String language){
        
        return adRepository.findAdsPreviews(city, language);
    }

    @Transactional
    public AdDTO retrieveAd(int id){

        AdDTO ad = adRepository.findAd(id);
        
        if(ad != null){
            return ad;
        } else {
            throw new AdNotFoundException();
        }
    }

    @Transactional
    public void deleteAd(int id){

        if(!adRepository.existsById(id)){throw new AdNotFoundException();}

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User teacher = userRepository.findByEmail(auth.getName());

        if(teacher.getAd() == null){
            throw new AdNotFoundException("Você não tem anúncios ainda");
        } else if(teacher.getAd().getId() == id){
            teacher.setAd(null);
            userRepository.save(teacher);
            adRepository.deleteById(id);
        } else {
            throw new AdNotFoundException("O anúncio não pertence a você");
        }
        
    }

    @Transactional
    public void updateAd(CreateAdDTO dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User teacher = userRepository.findByEmail(auth.getName());

        isTeacherValid(teacher);

        List<Language> languages = new ArrayList<>();

        for(String str : dto.languages()){
            if(isLanguageValid(str)){
                languages.add(languageRepository.findByName(str));
            } else {
                throw new LanguageNotFoundException();
            }
        }

        Ad ad = teacher.getAd();
        if(ad == null){throw new AdNotFoundException("Você ainda não tem um anúncio criado");}

        ad.setPricePerHour(dto.pricePerHour());
        ad.setTitle(dto.title());
        ad.setDescription(dto.description());
        ad.setCity(dto.city());
        ad.setPhoneNumber(dto.phoneNumber());
        ad.setModality(dto.modality());
        ad.setLanguages(languages);

        teacher.setAd(ad);
        userRepository.save(teacher);
    }

    private boolean isTeacherValid(User teacher){
        if(teacher == null ){
            throw new UserNotFoundException();
        }

        return true;
    }

    private boolean canCreateAd(User teacher){
        if(teacher.getAd() != null){
            throw new CantCreateAdException(teacher.getName() + ", você já tem um anúncio criado. É possível editar o seu anúncio atual ou o excluir!");
        }

        if(!teacher.getRole().name().equals("ROLE_TEACHER")){
            throw new CantCreateAdException();
        }

        return true;
    }

    private boolean isLanguageValid(String language){
        switch(language){
            case "Inglês": return true;
            case "Espanhol": return true;
            case "Italiano": return true;
            case "Francês": return true;
            case "Alemão": return true;
            case "Japonês": return true;
            case "Mandarim": return true;
            case "Russo": return true;
            case "Polonês": return true;
            case "Grego": return true;
            case "Árabe": return true;
            default: return false;
        }
    }

}
