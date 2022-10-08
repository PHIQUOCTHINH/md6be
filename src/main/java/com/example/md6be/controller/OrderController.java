package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    CustomerService customerService;

    @Autowired
    AppUserService appUserService;
    @Autowired
    OrderDetailService orderDetailService;
//// tạo cart ứng với id customer, mỗi customer có 1 cart
//    @PostMapping("/save-cart")
//    public ResponseEntity<Cart> saveCart(@RequestBody Customer customer){
//        Cart cart = new Cart();
//        cart.setCustomer(customer);
//        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
//    }
//    // test get cart để post
//    @GetMapping("/cart")
//    private ResponseEntity<List<Cart>> getCart(){
//        return new ResponseEntity<>(cartService.findAll(),HttpStatus.OK);
//    }
//// tạo item add vào cart
//    @PostMapping("/save-cart-detail")
//    public ResponseEntity<CartDetail> saveCartDetail(@RequestBody CartDetail cartDetail){
//        CartDetail newCartDetail = new CartDetail();
//        newCartDetail.setCart(cartDetail.getCart());
//        newCartDetail.setFood(cartDetail.getFood());
//        newCartDetail.setMerchant(cartDetail.getMerchant());
//        newCartDetail.setQuantity(cartDetail.getQuantity());
//        newCartDetail.setTotalPrice(cartDetail.getTotalPrice());
//        return new ResponseEntity<>(cartDetailService.save(newCartDetail),HttpStatus.OK);
//    }
//    // test get item(cart-detail) trong cart
//    @GetMapping("/find-bill-by-user-id/{id}")
//    private ResponseEntity<List<CartDetail>> getDetailCart(@PathVariable Long id){
//        return new ResponseEntity<>(cartDetailService.findAllCartDetailByUserId(id),HttpStatus.OK);
//    }
//// lưu order
//    @PostMapping("/save-order")
//    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
//        java.util.Date now = new java.util.Date();
//        Timestamp timestamp = new Timestamp(now.getTime());
//        Order newOrder = new Order();
//        newOrder.setCustomer(order.getCustomer());
//        newOrder.setMerchant(order.getMerchant());
//        newOrder.setPriceTotal(order.getPriceTotal());
//        newOrder.setCreateAt(timestamp);
//        return new ResponseEntity<>(orderService.save(newOrder),HttpStatus.OK);
//    }
//// lưu order detail
//    @GetMapping("/save-order-detail")
//    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail){
//        OrderDetail newOrderDetail = new OrderDetail();
//        newOrderDetail.setOrder(orderDetail.getOrder());
//        newOrderDetail.setFood(orderDetail.getFood());
//        newOrderDetail.setQuantity(orderDetail.getQuantity());
//        newOrderDetail.setPrice(orderDetail.getPrice());
//        return new ResponseEntity<>(orderDetailService.save(newOrderDetail),HttpStatus.OK);
//    }
//// tìm tất cả order ứng với id của merchant(xem danh sach đơn hàng của mình)
//    @GetMapping("/find-orders-by-user-id/{id}")
//    public ResponseEntity<List<Order>> findOrdersByCustomerId(@PathVariable Long id) {
//        return new ResponseEntity<>(orderService.findOrdersByUserId(id),HttpStatus.OK);
//    }
//
////    @GetMapping("/find-order-by-id")
////    public ResponseEntity<Order> findOrderById(@RequestBody Long idOrder) {
////        return new ResponseEntity<>(orderService.findOrderById(idOrder),HttpStatus.OK);
////    }
//  // xem order detail theo order id
//    @GetMapping("/find-order-details-by-order-id/{id}")
//    public ResponseEntity<List<OrderDetail>> findOrderDetailsByOrder(@PathVariable Long id) {
//        return new ResponseEntity<>(orderDetailService.findOrderDetailByOrderId(id),HttpStatus.OK);
//    }
//    // xem thông tin chi tiết của đơn hàng của mình
//    @GetMapping("/order-detail/{id}")
//    private ResponseEntity<OrderDetail> OrderDetail(@PathVariable Long id){
//        Optional<OrderDetail> orderDetail = orderDetailService.findById(id);
//        if (orderDetail.isPresent()) {
//            return new ResponseEntity<>(orderDetailService.findById(id).get(),HttpStatus.OK);
//        }
//        return null;
//    }
//    @PostMapping("/accept-order/{id}")
//    private ResponseEntity<Order> acceptOder(@PathVariable Long id ){
//        Optional<Order> order = orderService.findOrderById(id);
//        Order orderNew = order.get();
//        orderNew.setIsAccept(true);
//        orderService.save(orderNew);
////        sendMailController.sendEmail(newMerchant.getAppUser());
//        return new ResponseEntity<>(orderNew,HttpStatus.CREATED) ;
//    }
//    @DeleteMapping("/delete-order/{id}")
//    private ResponseEntity<Void> deleteOrder(@PathVariable long id){
//        Optional<Order> orderOptional = orderService.findOrderById(id);
//        if (orderOptional.isPresent()){
//            orderService.delete(id);
//        }
//        return null;
//    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findByUserId(@PathVariable Long id){
        return new ResponseEntity<>(cartService.findAllByCustomerId(id),HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<Cart> createCart(@RequestBody Cart cart){
        return new ResponseEntity<>(cartService.save(cart),HttpStatus.CREATED);
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
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @PostMapping("/item")
    private ResponseEntity<CartDetail> createItem(@RequestBody CartDetail cartDetail){
        return new ResponseEntity<>(cartDetailService.save(cartDetail),HttpStatus.CREATED);
    }
    @PutMapping("/item")
    private ResponseEntity<CartDetail> updateProduct(@RequestBody CartDetail cartDetail){
        Optional<CartDetail> optionalCartDetail = cartDetailService.findById(cartDetail.getId());
        if(optionalCartDetail.isPresent()){
            return new ResponseEntity<>(cartDetailService.save(cartDetail),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/create-order/{idUser}")
    private ResponseEntity<?> createOrder(@PathVariable Long idUser ){
        Optional<AppUser> appUser = appUserService.findByUserId(idUser);
        Customer customer = customerService.findCustomerByAppUser(appUser.get());
        List<CartDetail> cartDetailList = cartDetailService.findAllByUserId(idUser);
        Merchant merchant = cartDetailList.get(0).getFood().getMerchant();
        Order order = new Order();
        order.setCreateAt(LocalDateTime.now());
        order.setCustomer(customer);
        order.setMerchant(merchant);
        order.setIsAccept(false);
        orderService.save(order);
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ");
//        String createAt = format.format(order.getCreateAt());
//        System.out.println("day la: "+createAt);
        Order order1 = orderService.findLastOrder(customer.getId());
        if (order1 !=null){
            for (CartDetail cartDetail : cartDetailList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order1);
                orderDetail.setFood(cartDetail.getFood());
                orderDetail.setPrice(cartDetail.getFood().getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());
                orderDetailService.save(orderDetail);
                cartDetailService.delete(cartDetail.getId());
            }
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }
//    @GetMapping("/cancel-order/{idUser}")
//    private ResponseEntity<?> cancelOrder(@PathVariable Long idUser){
//        Optional<AppUser> appUser = appUserService.findByUserId(idUser);
//        Customer customer = customerService.findCustomerByAppUser(appUser.get());
//
//    }
//    @PutMapping("/update-all-cartDetail")
//    private ResponseEntity<?> updateAllCartDetail(@RequestBody Iterable<CartDetail> cartDetailList){
//        if ()
//    }

    @GetMapping("/cancel-order/{idUser}")
    private ResponseEntity<?> cancelOrder(@PathVariable Long idUser ){
        double total = 0;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Optional<AppUser> appUser = appUserService.findByUserId(idUser);
        Customer customer = customerService.findCustomerByAppUser(appUser.get());
        List<CartDetail> cartDetailList = cartDetailService.findAllByUserId(idUser);
        Merchant merchant = cartDetailList.get(0).getFood().getMerchant();
        Order order = new Order();
        order.setCreateAt(LocalDateTime.now());
        order.setCustomer(customer);
        order.setMerchant(merchant);
        order.setIsAccept(false);
        orderService.save(order);
        Order order1 = orderService.findLastOrder(customer.getId());
        if (order1 !=null){
            for (CartDetail cartDetail : cartDetailList) {
                total+= cartDetail.getQuantity()*cartDetail.getFood().getPrice();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order1);
                orderDetail.setFood(cartDetail.getFood());
                orderDetail.setPrice(cartDetail.getFood().getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());
                orderDetailList.add(orderDetail);
                order1.setPriceTotal(total);
                orderDetailService.save(orderDetail);
                cartDetailService.delete(cartDetail.getId());
            }
            return new ResponseEntity<>(orderDetailList,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }

}