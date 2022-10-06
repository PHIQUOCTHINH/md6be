package com.example.md6be.repository;

import com.example.md6be.model.Food;
import com.example.md6be.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<Food,Long> {
    List<Food> findFoodByMerchant(Merchant merchant);
    List<Food> findFoodsByMerchantId(Long id);
    @Query(value = "select*from food where merchant_id in (select merchant.id from merchant where user_id = :id )  ", nativeQuery = true)
    List<Food> findFoodsByUserId(@Param("id") Long id);
    @Query(value = "select * from food p where p.name like ?1 and merchant_id in (select id from merchant where user_id = ?2)", nativeQuery = true)
    List<Food> findFood( String name,Long id);
}
