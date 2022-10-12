package com.example.md6be.repository;

import com.example.md6be.model.Customer;
import com.example.md6be.model.Merchant;
import com.example.md6be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByMerchant(Merchant merchant);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where order_status_id = 1;")
    List<Order> findOrderByMerchantId(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where order_status_id = 2;")
    List<Order> findOrderConfirmedByMerchantId(long id);

    Order findOrderById(Long id);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update md6_case.orders set orders.order_status_id = 2 where orders.id =:idOrder")
    void confirmOrder(Long idOrder);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where customer_id =(select id from customer where user_id = :id) and is_accept =0;")
    List<Order> findWaitingOrdersByCustomerId (@Param("id") Long id);
//    List<Order> findOrderByCustomerId(Long id);
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where order_status_id =:id order by price_total asc;")
    List<Order> findOrdersConfirmedASC(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where order_status_id =:id order by price_total desc ;")
    List<Order> findOrdersConfirmedDESC(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where order_status_id =:id order by create_at asc;")
    List<Order> findOrdersConfirmedDateASC(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where order_status_id =:id order by create_at desc;")
    List<Order> findOrdersConfirmedDateDESC(int id);
//    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where create_at =:createAt and customer_id = :id;")
//    Order findByCreateAtAndAndCustomer(@Param("createAt") String createAt, @Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where orders.id =(select max(id) from md6_case.orders) and customer_id = :id ")
    Order findLastOrder(@Param("id")Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where merchant_id=(select id from merchant where user_id= ?1) and customer_id in (select id from customer where phone_number = ?1) ")
    List<Order> findOrderByPhoneNumber(String phoneNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where merchant_id=(select id from merchant where user_id= :id) and customer_id in (select id from customer where name like :name) ")
    List<Order> findOrderByNameCustomer(@Param("id") Long id,@Param("name") String name);
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where customer_id =(select id from customer where user_id = :id) and is_accept =1 and is_paid = 0;")
    List<Order> findAcceptedOrdersByCustomerId (@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.orders where customer_id =(select id from customer where user_id = :id) and is_accept =1 and is_paid = 1;")
    List<Order> findPaidOrdersByCustomerId (@Param("id") Long id);
    @Query(nativeQuery = true, value = "select*from orders where create_at BETWEEN ?1 AND ?2 ")
    List<Order> findOrderByCreateAt(String from,String to);
}