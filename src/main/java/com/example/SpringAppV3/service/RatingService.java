package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.RatingModel;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<RatingModel> findAll();
    Optional<RatingModel> findById(Long id);
    RatingModel save(RatingModel rating);
    void deleteById(Long id);
}
