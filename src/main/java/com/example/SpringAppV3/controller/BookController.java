package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.BookModel;
import com.example.SpringAppV3.service.AuthorService;
import com.example.SpringAppV3.service.BookService;
import com.example.SpringAppV3.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('BOOKMASTER') or hasRole('SUPERUSER')")
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService; // Добавьте AuthorService
    private final PublisherService publisherService; // Добавьте PublisherService
    private final PublisherService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService, PublisherService genreService) {
        this.bookService = bookService;
        this.authorService = authorService; // Инициализируйте AuthorService
        this.publisherService = publisherService; // Инициализируйте PublisherService
        this.genreService = genreService; // Инициализируйте PublisherService
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }
    @GetMapping("/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new BookModel());
        model.addAttribute("authors", authorService.findAll()); // Добавьте авторов
        model.addAttribute("publishers", publisherService.findAll()); // Добавьте издателей
        model.addAttribute("genres", genreService.findAll()); // Добавьте издателей
        return "books/form";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id)));
        model.addAttribute("authors", authorService.findAll()); // Добавьте авторов
        model.addAttribute("publishers", publisherService.findAll()); // Добавьте издателей
        model.addAttribute("genres", genreService.findAll()); // Добавьте издателей
        return "books/form";
    }


    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute BookModel book) {
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
