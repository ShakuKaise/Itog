package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.ReturnModel;

import java.util.List;
import java.util.Optional;

public interface ReturnService {
    List<ReturnModel> findAll();
    Optional<ReturnModel> findById(Long id);
    ReturnModel save(ReturnModel returnModel);
    void deleteById(Long id);
}
