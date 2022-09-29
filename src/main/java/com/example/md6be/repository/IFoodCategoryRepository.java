package com.example.md6be.repository;


import com.example.md6be.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodCategoryRepository extends JpaRepository<FoodCategory,Long> {
}
