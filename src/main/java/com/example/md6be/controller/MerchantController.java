package com.example.md6be.controller;

import com.example.md6be.model.Food;
import com.example.md6be.model.FoodCategory;
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
   // hiển thị danh sach món của chính merchant
    @GetMapping("/{id}")
    public ResponseEntity<List<Food>> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(foodService.findFoodsByUserId(id), HttpStatus.OK);
    }
    // detail cua món ăn
    @GetMapping("/food-detail/{id}")
    public ResponseEntity<Food> foodDetail(@PathVariable Long id) {
        Optional<Food> food = foodService.findById(id);
        return food.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // xoa mon an nhưng có thể ko dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<Food> delete(@PathVariable Long id) {
        foodService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
// create mon an
    @PostMapping
    public ResponseEntity<Food> create(@RequestBody Food food) {
        foodService.save(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
// update mon an
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Food food,
                                       @PathVariable Long id) {
        Optional<Food> productOptional = foodService.findById(id);
        if (productOptional.isPresent()) {
            food.setId(id);
            foodService.save(food);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // hien thi danh sach category
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
    // het hang
    @PostMapping("/active-ban-food/{id}")
    private ResponseEntity<Food> activeBanFood(@PathVariable Long id){
        Optional<Food> food = foodService.findById(id);
        Food activeBan = food.get();
        activeBan.setIsEmpty(!activeBan.getIsEmpty());
        foodService.save(activeBan);
        return new ResponseEntity<>(activeBan,HttpStatus.OK);
    }
    // tim theo ten gan dung
    @GetMapping("/find-food-like-name/{name}")
    private ResponseEntity<List<Food>> findLikeName(@PathVariable String name){
        return new ResponseEntity<>(foodService.findFood(name),HttpStatus.OK);
    }
}
