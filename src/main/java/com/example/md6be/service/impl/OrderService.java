package com.example.md6be.service.impl;


import com.example.md6be.repository.IOrderRepository;
import com.example.md6be.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;
}
