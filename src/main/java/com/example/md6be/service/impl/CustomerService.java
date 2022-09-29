package com.example.md6be.service.impl;


import com.example.md6be.repository.ICustomerRepository;
import com.example.md6be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;
}
