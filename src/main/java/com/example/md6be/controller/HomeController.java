package com.example.md6be.controller;

import com.example.md6be.model.Food;
import com.example.md6be.model.FoodCategory;
import com.example.md6be.model.Merchant;
import com.example.md6be.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    FoodService foodService;

    @Autowired
    FoodCategoryService foodCategoryService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    AppUserService appUserService;

    @Autowired
    CustomerService customerService;


    @GetMapping()
    public ResponseEntity<List<Food>> findAllFood(){
        List<Food> foods = foodService.findAll();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
        @GetMapping("/food-detail/{id}")
    public ResponseEntity<Food> findFoodById(@PathVariable Long id){
       Optional<Food> food = foodService.findById(id);
        return new ResponseEntity<>(food.get(), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<FoodCategory>> getAllCategories(){
        List<FoodCategory> categories = foodCategoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
//    @GetMapping("/category/{id}")
//    public ResponseEntity<List<Food>> filterByCategory(@PathVariable Long id){
//        Optional<FoodCategory> foodCategory = foodCategoryService.findById(id);
//        List<Food> foods = foodService.filterByCategory(id);
//        return new ResponseEntity<>(foods, HttpStatus.OK);
//    }

    @GetMapping("/merchant/{id}")
    public ResponseEntity<Merchant> findMerchantById(@PathVariable Long id){
        Merchant merchant = merchantService.findMerchantById(id);
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }

    @GetMapping("/food/{idMerchant}")
    public ResponseEntity<List<Food>> findFoodByMerchant(@PathVariable Long idMerchant){
        Merchant merchant = merchantService.findMerchantById(idMerchant);
        List<Food> foods = foodService.findFoodByMerchant(merchant);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
    @GetMapping("/merchant")
    public ResponseEntity<List<Merchant>> findAllMerchant(){
        List<Merchant> merchant = merchantService.findAllMerchant();
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }

    @GetMapping("/foods/{id}")
    public ResponseEntity<List<Food>> findAllByMerchant(@PathVariable Long idMerchant){
        Merchant merchant = merchantService.findMerchantById(idMerchant);
        List<Food> foods = foodService.findAllByMerchant(merchant) ;
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
