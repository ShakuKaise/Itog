package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.ReaderModel;
import com.example.SpringAppV3.repository.ReaderRepository;
import com.example.SpringAppV3.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public List<ReaderModel> findAll() {
        return readerRepository.findAll();
    }

    @Override
    public Optional<ReaderModel> findById(Long id) {
        return readerRepository.findById(id);
    }

    @Override
    public ReaderModel save(ReaderModel reader) {
        return readerRepository.save(reader);
    }

    @Override
    public void deleteById(Long id) {
        readerRepository.deleteById(id);
    }
}
