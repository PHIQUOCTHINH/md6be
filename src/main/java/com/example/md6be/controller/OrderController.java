package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    CartService cartService;
    @Autowired
    CartDetailService cartDetailService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;






    @Autowired
    CustomerService customerService;

    @Autowired
    AppUserService appUserService;
    @Autowired
    FoodService foodService;



    @GetMapping("/{id}")
    private ResponseEntity<?> findByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findAllByCustomerId(id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<Cart>> getAll() {
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/item")
    private ResponseEntity<List<CartDetail>> getAllItem() {
        return new ResponseEntity<>(cartDetailService.getAll(), HttpStatus.OK);
    }

    // Tìm kiếm List Item theo Id người dùng
    @GetMapping("/item/{idUser}")
    private ResponseEntity<List<CartDetail>> findItemByUserId(@PathVariable Long idUser) {
        return new ResponseEntity<>(cartDetailService.findAllByUserId(idUser), HttpStatus.OK);
    }

    @DeleteMapping("/item/{idItem}")
    private ResponseEntity<Void> deleteItemById(@PathVariable Long idItem) {
        cartDetailService.delete(idItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/item")
    private ResponseEntity<CartDetail> createItem(@RequestBody CartDetail cartDetail){
        Cart cart = cartService.findAllByCustomerId(cartDetail.getCart().getId());
        cartDetail.setCart(cart);
        return new ResponseEntity<>(cartDetailService.save(cartDetail),HttpStatus.CREATED);
    }


    @PutMapping("/item")
    private ResponseEntity<CartDetail> updateProduct(@RequestBody CartDetail cartDetail) {
        Optional<CartDetail> optionalCartDetail = cartDetailService.findById(cartDetail.getId());
        if (optionalCartDetail.isPresent()) {
            return new ResponseEntity<>(cartDetailService.save(cartDetail), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-orders-by-user-id/{id}")
    public ResponseEntity<List<Order>> findOrdersByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findOrdersByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/accept-order/{id}")
    private ResponseEntity<Order> acceptOder(@PathVariable Long id) {
        Order orderNew = orderService.findOrderById(id);
//        Order orderNew = order.get();
        orderNew.setIsAccept(true);
        orderService.save(orderNew);
//        sendMailController.sendEmail(newMerchant.getAppUser());
        return new ResponseEntity<>(orderNew, HttpStatus.CREATED);
    }

//    @DeleteMapping("/delete-order/{id}")
//    private ResponseEntity<Void> deleteOrder(@PathVariable long id) {
//        Optional<Order> orderOptional = orderService.findOrderById(id);
//        if (orderOptional.isPresent()) {
//            orderService.delete(id);
//        }
//        return null;
//    }

    @GetMapping("/find-order-details-by-order-id/{id}")
    public ResponseEntity<List<OrderDetail>> findOrderDetailsByOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderDetailService.findOrder(id), HttpStatus.OK);
    }







    @GetMapping("/create-order/{idUser}")
    private ResponseEntity<?> createOrder(@PathVariable Long idUser ){
        double total = 0;
        Optional<AppUser> appUser = appUserService.findByUserId(idUser);
        Customer customer = customerService.findCustomerByAppUser(appUser.get());
        List<CartDetail> cartDetailList = cartDetailService.findAllByUserId(idUser);
        Merchant merchant = cartDetailList.get(0).getFood().getMerchant();
        Order order = new Order();
        order.setCreateAt(LocalDateTime.now());
        order.setCustomer(customer);
        order.setMerchant(merchant);
        order.setIsAccept(false);
        order.setIsPaid(false);
        orderService.save(order);
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ");
//        String createAt = format.format(order.getCreateAt());
//        System.out.println("day la: "+createAt);
        Order order1 = orderService.findLastOrder(customer.getId());
        if (order1 !=null){
            for (CartDetail cartDetail : cartDetailList) {
                total+= cartDetail.getQuantity()*cartDetail.getFood().getPrice();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order1);
                orderDetail.setFood(cartDetail.getFood());
                orderDetail.setPrice(cartDetail.getFood().getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());
                order1.setPriceTotal(total);
                orderService.save(order1);
                cartDetail.getFood().setSold(cartDetail.getFood().getSold()+cartDetail.getQuantity());
                foodService.save(cartDetail.getFood());
                orderDetailService.save(orderDetail);
                cartDetailService.delete(cartDetail.getId());
            }
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/cancel-order/{id}")
    private ResponseEntity<?> cancelOrder(@PathVariable Long id ){
        List<OrderDetail> orderDetailList = orderDetailService.findOrderDetailByOrderId(id);
        orderDetailService.deleteByOrderId(orderDetailList);
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-order-by-customer/{id}")
    private ResponseEntity<List<Order>> findAllOrderByCustomer(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderService.findWaitingOrdersByCustomerId(id),HttpStatus.OK);
    }

    @GetMapping("/find-order-accepted-by-customer/{id}")
    private ResponseEntity<List<Order>> findAllOrderAcceptedByCustomer(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderService.findAcceptedOrdersByCustomerId(id),HttpStatus.OK);
    }

    @GetMapping("/pay-order/{id}")
    private ResponseEntity<?> payOrder(@PathVariable("id") Long id){
        Order order = orderService.findOrderById(id);
        order.setIsPaid(true);
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-order-detail-by-order/{id}")
    private ResponseEntity<List<OrderDetail>> findAllOrderDetailByOrderId(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderDetailService.findOrderDetailByOrderId(id),HttpStatus.OK);
    }


}


