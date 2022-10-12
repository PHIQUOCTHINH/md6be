package com.example.md6be.service;

import com.example.md6be.model.Merchant;
import com.example.md6be.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {
    List<Order> findOrderByMerchant(Merchant merchant);


    List<Order> findOrderByMerchantId(long id);

    Order save(Order order);


    List<Order> findOrderConfirmedByMerchantId(long id);

    Order findOrderById(Long id);

    void confirmOrder(Long idOrder);


    List<Order> findWaitingOrdersByCustomerId (Long idCustomer);

//    List<Order> findOrderByCustomerId(Long id);

    List<Order> findOrdersConfirmedASC(int id);


    List<Order> findOrdersConfirmedDESC(int id);


    List<Order> findOrdersConfirmedDateASC(int id);


    List<Order> findOrdersConfirmedDateDESC(int id);

    Order findLastOrder(Long id);
    List<Order> findOrderByPhoneNumber(String phoneNumber);

    List<Order> findOrderByNameCustomer(Long id,String name);
    List<Order> findPaidOrdersByCustomerId (Long id);
    List<Order> findOrderByCreateAt(String from,String to);
}
