package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.ReturnModel;
import com.example.SpringAppV3.repository.ReturnRepository;
import com.example.SpringAppV3.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReturnServiceImpl implements ReturnService {

    private final ReturnRepository returnRepository;

    @Autowired
    public ReturnServiceImpl(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    @Override
    public List<ReturnModel> findAll() {
        return returnRepository.findAll();
    }

    @Override
    public Optional<ReturnModel> findById(Long id) {
        return returnRepository.findById(id);
    }

    @Override
    public ReturnModel save(ReturnModel returnModel) {
        return returnRepository.save(returnModel);
    }

    @Override
    public void deleteById(Long id) {
        returnRepository.deleteById(id);
    }
}
