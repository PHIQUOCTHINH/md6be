package com.example.md6be.service;

import com.example.md6be.model.Merchant;
import com.example.md6be.model.Order;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> findOrderByMerchant(Merchant merchant);


    List<Order> findOrderByMerchantId(long id);

    Order save(Order order);


    List<Order> findOrderConfirmedByMerchantId(long id);

    Order findOrderById(Long id);

    void confirmOrder(Long idOrder);


//    List<Order> findOrdersByCustomerId (Long id);

    List<Order> findOrderByCustomerId(Long id);

    List<Order> findOrdersConfirmedASC(int id);


    List<Order> findOrdersConfirmedDESC(int id);


    List<Order> findOrdersConfirmedDateASC(int id);


    List<Order> findOrdersConfirmedDateDESC(int id);
    List<Order> findOrdersByUserId ( Long id);
    void delete(long id);


    Order findLastOrder(Long id);
    List<Order> findOrderByPhoneNumber(String phoneNumber);

    List<Order> findOrderByNameCustomer(Long id,String name);
    List<Order> findWaitingOrdersByCustomerId (Long idCustomer);
    List<Order> findPaidOrdersByCustomerId (@Param("id") Long id);
    List<Order> findOrderByCreateAt(String from,String to);
    List<Order> findAllPaidOrders(Long id);
}
