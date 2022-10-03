package com.example.md6be.service.impl;


import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import com.example.md6be.repository.ICustomerRepository;
import com.example.md6be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;
    @Override
    public Customer findCustomerByAppUser_Username(String userName) {
        return customerRepository.findCustomerByAppUser_Username(userName);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
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

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getWaitingAcceptCustomer() {
        return customerRepository.getWaitingAcceptCustomer();
    }

    @Override
    public List<Customer> findCustomerByIsAccept(Boolean isAccept) {
        return customerRepository.findCustomerByIsAccept(isAccept);
    }
    public Customer findCustomerByUserName(String userName){
        return customerRepository.findCustomerByAppUser_Username(userName);
    }
}
