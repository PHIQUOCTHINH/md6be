package com.example.md6be.service.impl;


import com.example.md6be.repository.ICartRepository;
import com.example.md6be.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepository cartRepository;
}
