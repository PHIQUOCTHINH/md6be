package com.example.md6be.repository;


import com.example.md6be.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail,Long> {
    @Query(value = "select*from cartdetail where merchant_id in (select merchant.id from merchant where user_id = :id )  ", nativeQuery = true)
    List<CartDetail> findAllCartDetailByUserId(@Param("id") Long id);
}
