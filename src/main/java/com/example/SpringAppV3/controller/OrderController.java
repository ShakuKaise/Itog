package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.OrderModel;
import com.example.SpringAppV3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('SUPERUSER')")
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders/list";
    }

    @GetMapping("/create")
    public String createOrderForm(Model model) {
        model.addAttribute("order", new OrderModel());
        return "orders/form";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute OrderModel order) {
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id)));
        return "orders/form";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable Long id, @ModelAttribute OrderModel order) {
        order.setId(id);
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }
}
