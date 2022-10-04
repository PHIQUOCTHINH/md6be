package com.example.md6be.service;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import com.example.md6be.model.Merchant;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    public void save(Customer customer);

    public Customer findCustomerByUserName(String userName);
    public List<Customer> getAllCustomers();

    public Optional<Customer> findCustomerById(Long id) ;

    public Customer saveCustomer(Customer customer) ;

    public List<Customer> findInActiveCustomer() ;

    public List<Customer> findActiveCustomer() ;

    public  Customer findCustomerByAppUser(AppUser appUser);
    List<Customer> getWaitingAcceptCustomer();
    List<Customer> findAll();
    List<Customer> findCustomerByIsAccept(Boolean isAccept);

}
