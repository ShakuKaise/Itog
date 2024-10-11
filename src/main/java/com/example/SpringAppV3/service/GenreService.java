package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.GenreModel;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreModel> findAll();
    Optional<GenreModel> findById(Long id);
    GenreModel save(GenreModel genre);
    void deleteById(Long id);
}
