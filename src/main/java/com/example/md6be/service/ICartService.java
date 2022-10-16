package com.example.md6be.service;

import com.example.md6be.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    Cart save(Cart cart);
    List<Cart> findAll();

    void delete(Long id);

    Optional<Cart> findById(Long id);

    Cart findAllByCustomerId(Long id);
}
