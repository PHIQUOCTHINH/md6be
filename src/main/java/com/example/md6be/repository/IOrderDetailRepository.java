package com.example.md6be.repository;

import com.example.md6be.model.Order;
import com.example.md6be.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orderdetails where order_id in (select id from md6_case.orders " +
            "where merchant_id = (select id from merchant where user_id = :id) " +
            "and customer_id in (select id from customer where name like :name) );")
    List<OrderDetail> findOrderDetailByNameCustomer(@Param("id")Long id, @Param("name")String name);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orderdetails where order_id in (select id from md6_case.orders where merchant_id in (select id from merchant where user_id =:id))")
    List<OrderDetail> findOrderDetailByUserId(@Param("id") Long id);


}
