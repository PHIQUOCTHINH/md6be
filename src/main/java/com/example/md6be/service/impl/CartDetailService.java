package com.example.md6be.service.impl;

import com.example.md6be.model.CartDetail;
import com.example.md6be.repository.ICartDetailRepository;
import com.example.md6be.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    ICartDetailRepository cartDetailRepository;

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public List<CartDetail> getAll() {
        return cartDetailRepository.findAll();
    }
}