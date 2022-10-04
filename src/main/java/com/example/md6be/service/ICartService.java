package com.example.md6be.service;

import com.example.md6be.model.Cart;

import java.util.List;

public interface ICartService {
    Cart save(Cart cart);
    List<Cart> findAll();
}