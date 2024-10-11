package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.ReaderModel;
import com.example.SpringAppV3.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('SUPERUSER')")
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public String listReaders(Model model) {
        model.addAttribute("readers", readerService.findAll());
        return "readers/list";
    }

    @GetMapping("/create")
    public String createReaderForm(Model model) {
        model.addAttribute("reader", new ReaderModel());
        return "readers/form";
    }

    @PostMapping("/create")
    public String createReader(@ModelAttribute ReaderModel reader) {
        readerService.save(reader);
        return "redirect:/readers";
    }

    @GetMapping("/edit/{id}")
    public String editReaderForm(@PathVariable Long id, Model model) {
        model.addAttribute("reader", readerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid reader Id:" + id)));
        return "readers/form";
    }

    @PostMapping("/edit/{id}")
    public String editReader(@PathVariable Long id, @ModelAttribute ReaderModel reader) {
        reader.setId(id);
        readerService.save(reader);
        return "redirect:/readers";
    }

    @GetMapping("/delete/{id}")
    public String deleteReader(@PathVariable Long id) {
        readerService.deleteById(id);
        return "redirect:/readers";
    }
}
