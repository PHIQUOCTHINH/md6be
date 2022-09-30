package com.example.md6be.service;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import com.example.md6be.model.Merchant;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Customer findCustomerByAppUser_Username (String userName);

    List<Customer> findAll();

    Optional<Customer> findCustomerById(Long id);


    List<Customer> findInActiveCustomer();


    List<Customer> findActiveCustomer();


    Customer findCustomerByAppUser(AppUser appUser);
    Customer save(Customer customer);

    List<Customer> getWaitingAcceptCustomer();
    List<Customer> findCustomerByIsAccept(Boolean isAccept);
}
