package com.example.md6be.service;

import com.example.md6be.model.Food;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IFoodService {
    Optional<Food> findById(Long id);

    void save(Food food);

    void delete(Long id);

    List<Food> findAll();
    List<Food> findAllByUserId(Long id);
    List<Food> findFood( String f,Long id);
    List<Food> findFoodByLikeName(Long id,String name);
    List<Food> findAllFoodByStatus();
    List<Food> findAllByFoodCategoryAndAndMerchantIsActive( Long id);
    List<Food> findAllByLikeName(String name);
}
