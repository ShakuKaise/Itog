package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.GenreModel;
import com.example.SpringAppV3.repository.GenreRepository;
import com.example.SpringAppV3.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreModel> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<GenreModel> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public GenreModel save(GenreModel genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
