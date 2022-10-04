package com.example.md6be.repository;


import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByAppUser_Username (String userName);

    List<Customer> findAll();

    Customer findCustomerById(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.customer where is_active = 0;")
    List<Customer> findInActiveCustomer();

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.customer where is_active = 1;")
    List<Customer> findActiveCustomer();


    Customer findCustomerByAppUser(AppUser appUser);
    @Query(nativeQuery = true, value = "select * from customer where is_accept = false;")
    List<Customer> getWaitingAcceptCustomer();
    List<Customer> findCustomerByIsAccept(Boolean isAccept);
}
