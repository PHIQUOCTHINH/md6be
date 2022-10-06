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
        Merchant merchant = merchantService.findByAppUserId(food.getMerchant().getId());
        food.setMerchant(merchant);
        foodService.save(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
// update mon an
@PutMapping()
public ResponseEntity<?> update(@RequestBody Food food) {
    Merchant merchant = merchantService.findByAppUserId(food.getMerchant().getId());
    food.setMerchant(merchant);
    foodService.save(food);
    return new ResponseEntity<>(HttpStatus.OK);
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
    @GetMapping("/find-food-like-name/{name}/{id}")
    private ResponseEntity<List<Food>> findLikeName(@PathVariable String name,@PathVariable Long id){
        return new ResponseEntity<>(foodService.findFood(name,id),HttpStatus.OK);
    }
    @GetMapping("/merchant/{id}")
    public ResponseEntity<Merchant> findMerchantById(@PathVariable Long id){
        Merchant merchant = merchantService.findMerchantById(id);
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }
    @GetMapping("/get-merchant-user/{id}")
    public ResponseEntity<Merchant> findMerchantByUserId(@PathVariable Long id){
        Merchant merchant = merchantService.findByAppUserId(id);
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }
    @PutMapping("/update-merchant/{id}")
    public ResponseEntity<Merchant> update(@RequestBody Merchant merchant) {
//        Merchant merchant1  = merchantService.findByAppUserId(merchant.getAppUser().getId());
        Merchant merchant1 = merchantService.findMerchantById(merchant.getId());
        merchantService.save(merchant1);
      return new ResponseEntity<>(merchant1, HttpStatus.OK);

    }

}
