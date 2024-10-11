package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.OrderModel;
import com.example.SpringAppV3.repository.OrderRepository;
import com.example.SpringAppV3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderModel> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderModel save(OrderModel order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
