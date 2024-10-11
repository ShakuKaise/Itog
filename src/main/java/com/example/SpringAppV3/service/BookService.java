package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.BookModel;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookModel> findAll();
    Optional<BookModel> findById(Long id);
    BookModel save(BookModel book);
    void deleteById(Long id);
}
