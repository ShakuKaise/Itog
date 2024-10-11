package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.ReturnModel;
import com.example.SpringAppV3.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('SUPERUSER')")
@Controller
@RequestMapping("/returns")
public class ReturnController {

    private final ReturnService returnService;

    @Autowired
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @GetMapping
    public String listReturns(Model model) {
        model.addAttribute("returns", returnService.findAll());
        return "returns/list";
    }

    @GetMapping("/create")
    public String createReturnForm(Model model) {
        model.addAttribute("return", new ReturnModel());
        return "returns/form";
    }

    @PostMapping("/create")
    public String createReturn(@ModelAttribute ReturnModel returnModel) {
        returnService.save(returnModel);
        return "redirect:/returns";
    }

    @GetMapping("/edit/{id}")
    public String editReturnForm(@PathVariable Long id, Model model) {
        model.addAttribute("return", returnService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid return Id:" + id)));
        return "returns/form";
    }

    @PostMapping("/edit/{id}")
    public String editReturn(@PathVariable Long id, @ModelAttribute ReturnModel returnModel) {
        returnModel.setId(id);
        returnService.save(returnModel);
        return "redirect:/returns";
    }

    @GetMapping("/delete/{id}")
    public String deleteReturn(@PathVariable Long id) {
        returnService.deleteById(id);
        return "redirect:/returns";
    }
}
