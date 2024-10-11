package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.ReaderModel;

import java.util.List;
import java.util.Optional;

public interface ReaderService {
    List<ReaderModel> findAll();
    Optional<ReaderModel> findById(Long id);
    ReaderModel save(ReaderModel reader);
    void deleteById(Long id);
}
