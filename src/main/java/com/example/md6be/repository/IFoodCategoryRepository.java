package com.example.md6be.repository;


import com.example.md6be.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodCategoryRepository extends JpaRepository<FoodCategory,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.food_categories;")
    List<FoodCategory> getAllCategory();

    FoodCategory findFoodCategoryById(Long id);
    List<FoodCategory> findAll();
}
