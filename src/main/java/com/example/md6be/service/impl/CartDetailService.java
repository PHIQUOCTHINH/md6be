package com.example.md6be.service.impl;

import com.example.md6be.repository.ICartDetailRepository;
import com.example.md6be.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    ICartDetailRepository cartDetailRepository;
}
