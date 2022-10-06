package com.example.md6be.repository;


import com.example.md6be.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select*from carts where customer_id in (select customer.id from customer where user_id =?1);", nativeQuery = true)
    List<Cart> findCartByCustomerId(Long id);
}
