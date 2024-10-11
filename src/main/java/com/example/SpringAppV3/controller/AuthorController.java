package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.AuthorModel;
import com.example.SpringAppV3.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('SUPERUSER')")
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    @GetMapping("/create")
    public String createAuthorForm(Model model) {
        model.addAttribute("author", new AuthorModel());
        return "authors/form";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute AuthorModel author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id)));
        return "authors/form";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id, @ModelAttribute AuthorModel author) {
        author.setId(id);
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
