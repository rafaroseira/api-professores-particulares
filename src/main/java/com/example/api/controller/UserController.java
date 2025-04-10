package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.CreateUserDTO;
import com.example.api.dto.UpdatePasswordDTO;
import com.example.api.dto.UpdateUserInfoDTO;
import com.example.api.dto.UserDTO;
import com.example.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid CreateUserDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso");
    }

    @PutMapping("/update-info")
    public ResponseEntity<String> updateInfo(@RequestBody @Valid UpdateUserInfoDTO dto){
            userService.updateInfo(dto);
            return ResponseEntity.ok().body("Informações pessoais atualizadas com sucesso!");
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody @Valid UpdatePasswordDTO dto){
            userService.updatePassword(dto);
            return ResponseEntity.ok().body("Senha atualizada com sucesso!");
    }

    @GetMapping("/user/{id}")
    public UserDTO retrieveUser(@PathVariable int id) {
        return userService.retrieveUser(id);
    }
    
}
