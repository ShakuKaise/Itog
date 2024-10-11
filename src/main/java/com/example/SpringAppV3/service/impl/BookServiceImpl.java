package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.BookModel;
import com.example.SpringAppV3.repository.BookRepository;
import com.example.SpringAppV3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookModel> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookModel save(BookModel book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
