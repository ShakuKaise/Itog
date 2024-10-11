package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.GenreModel;
import com.example.SpringAppV3.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('GENREMASTER') or hasRole('SUPERUSER')")
@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genres/list";
    }

    @GetMapping("/create")
    public String createGenreForm(Model model) {
        model.addAttribute("genre", new GenreModel());
        return "genres/form";
    }

    @PostMapping("/create")
    public String createGenre(@ModelAttribute GenreModel genre) {
        genreService.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String editGenreForm(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid genre Id:" + id)));
        return "genres/form";
    }

    @PostMapping("/edit/{id}")
    public String editGenre(@PathVariable Long id, @ModelAttribute GenreModel genre) {
        genre.setId(id);
        genreService.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }
}
