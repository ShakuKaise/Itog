package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.PublisherModel;
import com.example.SpringAppV3.repository.PublisherRepository;
import com.example.SpringAppV3.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherModel> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Optional<PublisherModel> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public PublisherModel save(PublisherModel publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
