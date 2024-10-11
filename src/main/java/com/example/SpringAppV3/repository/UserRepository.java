package com.example.SpringAppV3.repository;

import com.example.SpringAppV3.model.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    public boolean existsByEmail(@Email String email);

}