package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.RatingModel;
import com.example.SpringAppV3.repository.RatingRepository;
import com.example.SpringAppV3.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<RatingModel> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<RatingModel> findById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public RatingModel save(RatingModel rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }
}
