package com.example.md6be.service.impl;
import com.example.md6be.model.Cart;
import com.example.md6be.repository.ICartRepository;
import com.example.md6be.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }
    @Override
    public void delete(Long id){
        cartRepository.deleteById(id);
    }
    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }
    @Override
    public List<Cart> findAllByCustomerId(Long id) {
        return cartRepository.findCartByCustomerId(id);
    }
}