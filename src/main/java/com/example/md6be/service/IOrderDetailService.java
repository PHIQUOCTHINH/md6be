package com.example.md6be.service;

import com.example.md6be.model.Order;
import com.example.md6be.model.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    List<OrderDetail> findOrderDetailByOrder(Order order);


    List<OrderDetail> findOrder( Long id);


    OrderDetail findOrderDetailByIdOrderDetail(Long idOrderDetail);
    OrderDetail save(OrderDetail orderDetail);
    Optional<OrderDetail> findById(Long id);
    List<OrderDetail> findOrderDetailByNameCustomer(Long id,String name);
    List<OrderDetail> findOrderDetailByUserId(@Param("id") Long id);





    List<OrderDetail> findOrderDetailByOrderId(Long idOrder);




}
