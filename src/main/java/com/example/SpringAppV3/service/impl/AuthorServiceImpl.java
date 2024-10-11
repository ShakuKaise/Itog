package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.AuthorModel;
import com.example.SpringAppV3.repository.AuthorRepository;
import com.example.SpringAppV3.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorModel> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<AuthorModel> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public AuthorModel save(AuthorModel author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
