package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.service.impl.*;
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
    @Autowired
    OrderService orderRepository;
    @Autowired
    OrderDetailService iOrderDetailService;
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
    @PutMapping("/update-merchant")
    public ResponseEntity<?> update(@RequestBody Merchant merchant) {
        Optional<Merchant> merchant2 = Optional.ofNullable(merchantService.findByAppUserId(merchant.getAppUser().getId()));
        if (merchant2.isPresent()){
            merchant.setId(merchant2.get().getId());;
            merchant.setIsActive(merchant2.get().getIsActive());
            merchant.setIsAccept(merchant2.get().getIsAccept());
            merchant.setAvatar(merchant2.get().getAvatar());
            merchant.setImageBanner(merchant2.get().getImageBanner());
            merchantService.save(merchant);
            return new ResponseEntity<>(merchant,HttpStatus.OK);
        }
        return null;
    }
    @GetMapping("/find-order-by-name/{id}/{name}")
    public ResponseEntity<List<Order>> findAllOrderByLikeName(@PathVariable("id") Long id, @PathVariable("name") String name) {
        List<Order> orders = orderRepository.findOrderByNameCustomer(id,"%"+name+"%");
        System.out.println(orders);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/find-order-detail-by-name-customer/{id}/{name}")
    public ResponseEntity<List<OrderDetail>> findAllOrderDetailByNameCustomer(@PathVariable("id") Long id,@PathVariable("name") String name) {
        List<OrderDetail> orderDetailList = iOrderDetailService.findOrderDetailByNameCustomer(id,"%"+name+"%");
        return new ResponseEntity<>(orderDetailList,HttpStatus.OK);
    }
    @GetMapping("find-order-detail-by-user-id/{id}")
    public  ResponseEntity<List<OrderDetail>> findOrderDetailByUserId(@PathVariable Long id){
        List<OrderDetail> orderDetails = iOrderDetailService.findOrderDetailByUserId(id);
        return new ResponseEntity<>(orderDetails,HttpStatus.OK);
    }

}
