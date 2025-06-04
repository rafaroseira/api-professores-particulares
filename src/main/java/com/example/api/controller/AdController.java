package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.AdDTO;
import com.example.api.dto.AdPreviewDTO;
import com.example.api.dto.CreateAdDTO;
import com.example.api.service.AdService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AdController {

    @Autowired
    private AdService adService;

    @PostMapping(value = "/create-ad")
    public ResponseEntity<String> createAd(@RequestBody @Valid CreateAdDTO dto) {
        adService.createAd(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Anúncio criado com sucesso");
    }

    @PutMapping(value = "/update-ad")
    public ResponseEntity<String> updateAd(@RequestBody @Valid CreateAdDTO dto) {
        adService.updateAd(dto);
        return ResponseEntity.ok().body("Anúncio atualizado com sucesso!");
    }

    @GetMapping("/show-previews")
    public List<AdPreviewDTO> showPreviews(@RequestParam String city, @RequestParam String language) {
        return adService.preview(city,language);
    }

    @GetMapping("/ad/{id}")
    public AdDTO retrieveAd(@PathVariable int id) {
        return adService.retrieveAd(id);
    }

    @DeleteMapping("/ad/{id}")
    public ResponseEntity<String> deleteAd(@PathVariable int id){
        adService.deleteAd(id);
        return ResponseEntity.ok().body("Anúncio excluído com sucesso!");
    }
}
