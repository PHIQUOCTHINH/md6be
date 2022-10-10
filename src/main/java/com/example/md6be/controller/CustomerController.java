package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.repository.IFoodRepository;
import com.example.md6be.service.IFoodCategoryService;
import com.example.md6be.service.IFoodService;
import com.example.md6be.service.impl.AppUserService;
import com.example.md6be.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    IFoodService foodService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AppUserService appUserService;

    @Autowired
    IFoodCategoryService foodCategoryService;
    @GetMapping()
    private ResponseEntity<List<Food>> getAllFood(){
        return new ResponseEntity<>(foodService.findAllFoodByStatus(), HttpStatus.OK);
    }
    @GetMapping("/cate")
    private ResponseEntity<List<FoodCategory>> getAllCategory(){
        return new ResponseEntity<>(foodCategoryService.getAllCategory(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<Food>> findFoodById(@PathVariable("id") Long id){
        return new ResponseEntity<>(foodService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/get-all-by-cate/{id}")
    private ResponseEntity<List<Food>> getAllByCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(foodService.findAllByFoodCategoryAndAndMerchantIsActive(id),HttpStatus.OK);
    }

    @GetMapping("/find-all-by-like-name/{name}")
    private ResponseEntity<List<Food>> findFoodByLikeName(@PathVariable("name") String name){
        return new ResponseEntity<>(foodService.findAllByLikeName("%"+name+"%"),HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        Optional<AppUser> appUser = appUserService.findByUserId(customer.getAppUser().getId());
        Customer customer1 = customerService.findCustomerByAppUser(appUser.get());
       if (customer.getAvatar()!=null){
           customer1.setAvatar(customer.getAvatar());
       }
       if (customer.getAddress()!=null){
           customer1.setAddress(customer.getAddress());
       }
        if (customer.getName()!=null){
            customer1.setName(customer.getName());
        }
        if (customer.getPhoneNumber()!=null){
            customer1.setPhoneNumber(customer.getPhoneNumber());
        }
        customerService.save(customer1);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/customer/{id}")
    private ResponseEntity<?> findCustomerById(@PathVariable("id") Long id){
        Optional<AppUser> appUser = appUserService.findByUserId(id);
        if (appUser.isPresent()){
            Customer customer = customerService.findCustomerByAppUser(appUser.get());
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
