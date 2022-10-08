package com.example.md6be.controller;

import com.example.md6be.model.Food;
import com.example.md6be.model.FoodCategory;
import com.example.md6be.repository.IFoodRepository;
import com.example.md6be.service.IFoodCategoryService;
import com.example.md6be.service.IFoodService;
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

}
