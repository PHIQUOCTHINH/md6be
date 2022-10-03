package com.example.md6be.service;

import com.example.md6be.model.Order;
import com.example.md6be.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    List<OrderDetail> findOrderDetailByOrder(Order order);


    List<OrderDetail> findOrderDetailByOrderId(Long idOrder);


    OrderDetail findOrderDetailByIdOrderDetail(Long idOrderDetail);
    OrderDetail save(OrderDetail orderDetail);
    Optional<OrderDetail> findById(Long id);
}
