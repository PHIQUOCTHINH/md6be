package com.example.md6be.controller;

import com.example.md6be.model.Food;
import com.example.md6be.model.FoodCategory;
import com.example.md6be.model.Merchant;
import com.example.md6be.service.impl.FoodCategoryService;
import com.example.md6be.service.impl.FoodService;
import com.example.md6be.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/merchant")
public class MerchantController {
    @Autowired
    FoodService foodService;
    @Autowired
    MerchantService merchantService;

    @Autowired
    FoodCategoryService foodCategoryService;
    @GetMapping("/{id}")
    public ResponseEntity<List<Food>> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(foodService.findAllByUserId(id), HttpStatus.OK);
    }
    @GetMapping("/food-detail/{id}")
    public ResponseEntity<Food> foodDetail(@PathVariable Long id) {
        Optional<Food> food = foodService.findById(id);
        return food.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Food> delete(@PathVariable Long id) {
        foodService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Food> create(@RequestBody Food food) {
        Merchant merchant = merchantService.findByAppUserId(food.getMerchant().getId());
        food.setMerchant(merchant);
        System.out.println(food);
        foodService.save(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Food food) {
        Merchant merchant = merchantService.findByAppUserId(food.getMerchant().getId());
        food.setMerchant(merchant);
            foodService.save(food);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/category")
    public ResponseEntity<List<FoodCategory>> findAllCategory() {
        return new ResponseEntity<>(foodCategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<FoodCategory> findOneCategory(@PathVariable Long id) {
        Optional<FoodCategory> category = foodCategoryService.findById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/active-ban-food/{id}")
    private ResponseEntity<Food> activeBanFood(@PathVariable Long id){
        Optional<Food> food = foodService.findById(id);
        Food activeBan = food.get();
        System.out.println(activeBan);
        activeBan.setIsEmpty(!activeBan.getIsEmpty());
        foodService.save(activeBan);
        return new ResponseEntity<>(activeBan,HttpStatus.OK);
    }
}
