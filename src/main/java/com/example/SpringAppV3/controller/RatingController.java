package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.RatingModel;
import com.example.SpringAppV3.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('SUPERUSER')")
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public String listRatings(Model model) {
        model.addAttribute("ratings", ratingService.findAll());
        return "ratings/list";
    }

    @GetMapping("/create")
    public String createRatingForm(Model model) {
        model.addAttribute("rating", new RatingModel());
        return "ratings/form";
    }

    @PostMapping("/create")
    public String createRating(@ModelAttribute RatingModel rating) {
        ratingService.save(rating);
        return "redirect:/ratings";
    }

    @GetMapping("/edit/{id}")
    public String editRatingForm(@PathVariable Long id, Model model) {
        model.addAttribute("rating", ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id)));
        return "ratings/form";
    }

    @PostMapping("/edit/{id}")
    public String editRating(@PathVariable Long id, @ModelAttribute RatingModel rating) {
        rating.setId(id);
        ratingService.save(rating);
        return "redirect:/ratings";
    }

    @GetMapping("/delete/{id}")
    public String deleteRating(@PathVariable Long id) {
        ratingService.deleteById(id);
        return "redirect:/ratings";
    }
}
