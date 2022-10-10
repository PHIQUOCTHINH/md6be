package com.example.md6be.service.impl;


import com.example.md6be.model.Order;
import com.example.md6be.model.OrderDetail;
import com.example.md6be.repository.IOrderDetailRepository;
import com.example.md6be.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    IOrderDetailRepository detailRepository;

    @Override
    public List<OrderDetail> findOrderDetailByOrder(Order order) {
        return detailRepository.findOrderDetailByOrder(order);
    }

    @Override
    public  List<OrderDetail> findOrder( Long id) {
        return detailRepository.findOrderDetailByOrderId(id);
    }

    @Override
    public OrderDetail findOrderDetailByIdOrderDetail(Long idOrderDetail) {
        return detailRepository.findOrderDetailByIdOrderDetail(idOrderDetail);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return detailRepository.save(orderDetail);
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return detailRepository.findById(id);
    }
    @Override
    public List<OrderDetail> findOrderDetailByNameCustomer(Long id, String name) {
        return detailRepository.findOrderDetailByNameCustomer(id,name);
    }

    @Override
    public List<OrderDetail> findOrderDetailByUserId(Long id) {
        return detailRepository.findOrderDetailByUserId(id);
    }

}
