package com.example.md6be.repository;

import com.example.md6be.model.Food;
import com.example.md6be.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<Food,Long> {
    List<Food> findFoodByMerchant(Merchant merchant);
    List<Food> findFoodsByMerchantId(Long id);
}
