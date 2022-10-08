package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.repository.IOrderDetailRepository;
import com.example.md6be.repository.IOrderRepository;
import com.example.md6be.service.impl.FoodCategoryService;
import com.example.md6be.service.impl.FoodService;
import com.example.md6be.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
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
    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IOrderDetailRepository iOrderDetailRepository;
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

    @GetMapping("/find-by-name/{id}/{name}")
    public ResponseEntity<List<Food>> findAllByLikeName(@PathVariable("id") Long id, @PathVariable("name") String name) {
        return new ResponseEntity<>(foodService.findFoodByLikeName(id, name), HttpStatus.OK);
    }
    @GetMapping("/find-order-by-name/{id}/{name}")
    public ResponseEntity<List<Order>> findAllOrderByLikeName(@PathVariable("id") Long id, @PathVariable("name") String name) {
        List<Order> orders = orderRepository.findOrderByNameCustomer(id,"%"+name+"%");
        System.out.println(orders);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/find-order-detail-by-order_id/{id}")
    public ResponseEntity<List<OrderDetail>> findAllOrderDetailByOrderId(@PathVariable("id") Long id) {
        List<OrderDetail> orderDetailList = iOrderDetailRepository.findOrderDetailByOrderId(id);
        return new ResponseEntity<>(orderDetailList,HttpStatus.OK);
    }

    @GetMapping("/find-order-detail-by-name-customer/{id}/{name}")
    public ResponseEntity<List<OrderDetail>> findAllOrderDetailByNameCustomer(@PathVariable("id") Long id,@PathVariable("name") String name) {
        List<OrderDetail> orderDetailList = iOrderDetailRepository.findOrderDetailByNameCustomer(id,"%"+name+"%");
        return new ResponseEntity<>(orderDetailList,HttpStatus.OK);
    }

}
