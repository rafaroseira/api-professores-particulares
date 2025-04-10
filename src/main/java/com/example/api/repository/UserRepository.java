package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findById(int id);
    User findByAdId(int id);
    User findByEmail(String email);
}