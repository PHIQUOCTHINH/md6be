package com.example.md6be.repository;


import com.example.md6be.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail,Long> {
    @Query(value = "select * from cartdetail where cart_id = (select carts.id from carts where customer_id = (select customer.id from customer where user_id = ?1))", nativeQuery = true)
    List<CartDetail> findAllByUserId( Long id);
}
