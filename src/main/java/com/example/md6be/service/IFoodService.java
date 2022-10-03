package com.example.md6be.service;

import com.example.md6be.model.Food;

import java.util.List;
import java.util.Optional;

public interface IFoodService {
    Optional<Food> findById(Long id);

    void save(Food food);

    void delete(Long id);

    List<Food> findAll();
}
