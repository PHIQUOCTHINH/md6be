package com.example.md6be.service.impl;


import com.example.md6be.model.Merchant;
import com.example.md6be.model.Order;
import com.example.md6be.repository.IOrderRepository;
import com.example.md6be.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;

    @Override
    public List<Order> findOrderByMerchant(Merchant merchant) {
        return orderRepository.findOrderByMerchant(merchant);
    }

    @Override
    public List<Order> findOrderByMerchantId(long id) {
        return orderRepository.findOrderByMerchantId(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderConfirmedByMerchantId(long id) {
        return orderRepository.findOrderConfirmedByMerchantId(id);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public void confirmOrder(Long idOrder) {
    orderRepository.confirmOrder(idOrder);
    }

//    @Override
//    public List<Order> findOrdersByCustomerId(Long id) {
//        return orderRepository.findOrdersByCustomerId(id);
//    }

    @Override
    public List<Order> findOrderByCustomerId(Long id) {
        return orderRepository.findOrderByCustomerId(id);
    }

    @Override
    public List<Order> findOrdersConfirmedASC(int id) {
        return orderRepository.findOrdersConfirmedASC(id);
    }

    @Override
    public List<Order> findOrdersConfirmedDESC(int id) {
        return orderRepository.findOrdersConfirmedDESC(id);
    }

    @Override
    public List<Order> findOrdersConfirmedDateASC(int id) {
        return orderRepository.findOrdersConfirmedDateASC(id);
    }

    @Override
    public List<Order> findOrdersConfirmedDateDESC(int id) {
        return orderRepository.findOrdersConfirmedDateDESC(id);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id) {
        return orderRepository.findOrdersByUserId(id);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
    @Override
    public Order findLastOrder(Long id) {
        return orderRepository.findLastOrder(id);
    }

    @Override
    public List<Order> findOrderByPhoneNumber(String phoneNumber) {
        return orderRepository.findOrderByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Order> findOrderByNameCustomer(Long id,String name) {
        return orderRepository.findOrderByNameCustomer(id ,name);
    }

    @Override
    public List<Order> findWaitingOrdersByCustomerId(Long idCustomer) {
        return orderRepository.findWaitingOrdersByCustomerId(idCustomer);
    }
    public List<Order> findAcceptedOrdersByCustomerId(Long id){
        return orderRepository.findAcceptedOrdersByCustomerId(id);
    }
}
