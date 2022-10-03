package com.example.md6be.service.impl;


import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import com.example.md6be.repository.ICustomerRepository;
import com.example.md6be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByUserName(String userName) {
        return customerRepository.findCustomerByAppUser_Username(userName);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findInActiveCustomer() {
        return customerRepository.findInActiveCustomer();
    }

    @Override
    public List<Customer> findActiveCustomer() {
        return customerRepository.findActiveCustomer();
    }

    @Override
    public Customer findCustomerByAppUser(AppUser appUser) {
        return customerRepository.findCustomerByAppUser(appUser);
    }
}
