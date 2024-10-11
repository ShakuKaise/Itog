package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.OrderModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderModel> findAll();
    Optional<OrderModel> findById(Long id);
    OrderModel save(OrderModel order);
    void deleteById(Long id);
}
