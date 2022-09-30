package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.service.impl.CartDetailService;
import com.example.md6be.service.impl.CartService;
import com.example.md6be.service.impl.OrderDetailService;
import com.example.md6be.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
// tạo cart ứng với id customer, mỗi customer có 1 cart
    @PostMapping("/save-cart")
    public ResponseEntity<Cart> saveCart(@RequestBody Customer customer){
        Cart cart = new Cart();
        cart.setCustomer(customer);
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
    }
    // test get cart để post
    @GetMapping("/cart")
    private ResponseEntity<List<Cart>> getCart(){
        return new ResponseEntity<>(cartService.findAll(),HttpStatus.OK);
    }
// tạo item add vào cart
    @PostMapping("/save-cart-detail")
    public ResponseEntity<CartDetail> saveCartDetail(@RequestBody CartDetail cartDetail){
        CartDetail newCartDetail = new CartDetail();
        newCartDetail.setCart(cartDetail.getCart());
        newCartDetail.setFood(cartDetail.getFood());
        newCartDetail.setMerchant(cartDetail.getMerchant());
        newCartDetail.setQuantity(cartDetail.getQuantity());
        newCartDetail.setTotalPrice(cartDetail.getTotalPrice());
        return new ResponseEntity<>(cartDetailService.save(newCartDetail),HttpStatus.OK);
    }
    // test get item(cart-detail) trong cart
    @GetMapping("cart-detail")
    private ResponseEntity<List<CartDetail>> getDetailCart(){
        return new ResponseEntity<>(cartDetailService.getAll(),HttpStatus.OK);
    }
// lưu order
    @PostMapping("/save-order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        java.util.Date now = new java.util.Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        Order newOrder = new Order();
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setMerchant(order.getMerchant());
        newOrder.setPriceTotal(order.getPriceTotal());
        newOrder.setCreateAt(timestamp);
        return new ResponseEntity<>(orderService.save(newOrder),HttpStatus.OK);
    }
// lưu order detail
    @GetMapping("/save-order-detail")
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail){
        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setOrder(orderDetail.getOrder());
        newOrderDetail.setFood(orderDetail.getFood());
        newOrderDetail.setQuantity(orderDetail.getQuantity());
        newOrderDetail.setPrice(orderDetail.getPrice());
        return new ResponseEntity<>(orderDetailService.save(newOrderDetail),HttpStatus.OK);
    }
// tìm tất cả order ứng với id của customer(xem danh sach đơn hàng của mình)
    @GetMapping("/find-orders-by-customer-id/{id}")
    public ResponseEntity<List<Order>> findOrdersByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findOrderByCustomerId(id),HttpStatus.OK);
    }

    @GetMapping("/find-order-by-id")
    public ResponseEntity<Order> findOrderById(@RequestBody Long idOrder) {
        return new ResponseEntity<>(orderService.findOrderById(idOrder),HttpStatus.OK);
    }
  // xem order detail theo order id
    @GetMapping("/find-order-details-by-order-id/{id}")
    public ResponseEntity<List<OrderDetail>> findOrderDetailsByOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailByOrderId(id),HttpStatus.OK);
    }
    // xem thông tin chi tiết của đơn hàng của mình
    @GetMapping("/order-detail/{id}")
    private ResponseEntity<OrderDetail> OrderDetail(@PathVariable Long id){
        Optional<OrderDetail> orderDetail = orderDetailService.findById(id);
        if (orderDetail.isPresent()) {
            return new ResponseEntity<>(orderDetailService.findById(id).get(),HttpStatus.OK);
        }
        return null;
    }
}
