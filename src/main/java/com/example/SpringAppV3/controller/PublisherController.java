package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.PublisherModel;
import com.example.SpringAppV3.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('SUPERUSER')")
@Controller
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String listPublishers(Model model) {
        model.addAttribute("publishers", publisherService.findAll());
        return "publishers/list";
    }

    @GetMapping("/create")
    public String createPublisherForm(Model model) {
        model.addAttribute("publisher", new PublisherModel());
        return "publishers/form";
    }

    @PostMapping("/create")
    public String createPublisher(@ModelAttribute PublisherModel publisher) {
        publisherService.save(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/edit/{id}")
    public String editPublisherForm(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid publisher Id:" + id)));
        return "publishers/form";
    }

    @PostMapping("/edit/{id}")
    public String editPublisher(@PathVariable Long id, @ModelAttribute PublisherModel publisher) {
        publisher.setId(id);
        publisherService.save(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherService.deleteById(id);
        return "redirect:/publishers";
    }
}
