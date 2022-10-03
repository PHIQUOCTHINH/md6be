package com.example.md6be.controller;

import com.example.md6be.model.Customer;
import com.example.md6be.model.FoodComment;
import com.example.md6be.model.Merchant;
import com.example.md6be.service.impl.AppUserService;
import com.example.md6be.service.impl.CustomerService;
import com.example.md6be.service.impl.FoodCommentService;
import com.example.md6be.service.impl.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired

    FoodService foodService;

    @Autowired
    FoodCommentService foodCommentService;

    @Autowired
    AppUserService appUserService;

//    @PostMapping("/findCustomerByUserName")
//    public ResponseEntity<Customer> findCustomerByUserID(@RequestBody String userName){
//        Customer customer = customerService.findCustomerByUserName(userName);
//        return new ResponseEntity<>(customer, HttpStatus.OK) ;
//    }

//    @PostMapping("/findMerchantByFoodId")
//    public ResponseEntity<Merchant> findSellerByProductId(@RequestBody Long id){
//
//        return new ResponseEntity<Merchant>(foodService.findById(id).get(), HttpStatus.OK);
//    }

    @PostMapping("/save-productComment")
    public ResponseEntity<FoodComment> save(@RequestBody FoodComment foodComment){
        java.util.Date now = new java.util.Date();
        foodComment.setCreatAt(now);
        return new ResponseEntity<FoodComment>(foodCommentService.save(foodComment), HttpStatus.OK);
    }

//    @PostMapping("/findProductCommentListByProductId")
//    public ResponseEntity<List<FoodComment>> findProductCommentListByProductId(@RequestBody Long idProduct){
//
//        return new ResponseEntity<List<FoodComment>>(foodCommentService.findProductCommentListByProductId(idProduct), HttpStatus.OK);
//    }

    // Edit profile Customer
    @PostMapping("/edit-customer")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer){
        appUserService.save(customer.getAppUser());
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

}
