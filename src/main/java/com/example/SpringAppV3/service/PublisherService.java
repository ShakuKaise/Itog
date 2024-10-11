package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.PublisherModel;
import com.example.SpringAppV3.model.RatingModel;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    List<PublisherModel> findAll();
    Optional<PublisherModel> findById(Long id);
    PublisherModel save(PublisherModel publisher);
    void deleteById(Long id);
}