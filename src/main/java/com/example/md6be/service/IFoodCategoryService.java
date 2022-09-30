package com.example.md6be.service;

import com.example.md6be.model.FoodCategory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IFoodCategoryService {

    List<FoodCategory> getAllCategory();

    FoodCategory findFoodCategoryById(Long id);
    List<FoodCategory> findAll();
    Optional<FoodCategory> findById(Long id);
}
