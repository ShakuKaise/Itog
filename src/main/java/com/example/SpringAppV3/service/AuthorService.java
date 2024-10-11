package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.AuthorModel;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorModel> findAll();
    Optional<AuthorModel> findById(Long id);
    AuthorModel save(AuthorModel author);
    void deleteById(Long id);
}
