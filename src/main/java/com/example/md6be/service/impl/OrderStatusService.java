package com.example.md6be.service.impl;

import com.example.md6be.repository.IOrderStatusRepository;
import com.example.md6be.service.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService implements IOrderStatusService {
    @Autowired
    IOrderStatusRepository orderStatusRepository;

}
