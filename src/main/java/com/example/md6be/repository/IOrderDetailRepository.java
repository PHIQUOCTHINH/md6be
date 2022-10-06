package com.example.md6be.repository;

import com.example.md6be.model.Order;
import com.example.md6be.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    List<OrderDetail> findOrderDetailByOrder(Order order);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orderdetails where order_id =:idOrder ;")
    List<OrderDetail> findOrderDetailByOrderId(Long idOrder);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orderdetails where id =:idOrderDetail ;")
    OrderDetail findOrderDetailByIdOrderDetail(Long idOrderDetail);
    Optional<OrderDetail> findById(Long id);
}
