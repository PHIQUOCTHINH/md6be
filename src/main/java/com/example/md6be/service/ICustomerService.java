package com.example.md6be.service;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;

import java.util.List;

public interface ICustomerService {
    public void save(Customer customer);

    public Customer findCustomerByUserName(String userName);
    public List<Customer> getAllCustomers();

    public Customer findCustomerById(Long id) ;

    public Customer saveCustomer(Customer customer) ;

    public List<Customer> findInActiveCustomer() ;

    public List<Customer> findActiveCustomer() ;

    public  Customer findCustomerByAppUser(AppUser appUser);
}
