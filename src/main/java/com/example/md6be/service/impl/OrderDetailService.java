package com.example.md6be.service.impl;


import com.example.md6be.repository.IOrderDetailRepository;
import com.example.md6be.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    IOrderDetailRepository detailRepository;
}
