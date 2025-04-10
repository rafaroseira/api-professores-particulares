package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.FeedbackDTO;
import com.example.api.dto.PostFeedbackDTO;
import com.example.api.service.FeedbackService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/post-feedback")
    public ResponseEntity<String> postFeedback(@RequestBody @Valid PostFeedbackDTO dto) {
        
        feedbackService.postFeedback(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação registrada com sucesso");
    }

   @GetMapping("/feedbacks/teacher/{id}")
   public List<FeedbackDTO> getTeacherFeedback(@PathVariable int id) {
       return feedbackService.getTeacherFeedback(id);
   }
   
}
