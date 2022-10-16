package com.example.md6be.service.impl;


import com.example.md6be.model.FoodCategory;
import com.example.md6be.repository.IFoodCategoryRepository;
import com.example.md6be.service.IFoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryService implements IFoodCategoryService {
    @Autowired
    IFoodCategoryRepository foodCategoryRepository;
    @Override
    public List<FoodCategory> getAllCategory() {
        return foodCategoryRepository.getAllCategory();
    }

    @Override
    public FoodCategory findFoodCategoryById(Long id) {
        return foodCategoryRepository.findFoodCategoryById(id);
    }

    @Override
    public List<FoodCategory> findAll() {
        return foodCategoryRepository.findAll();
    }

    @Override
    public Optional<FoodCategory> findById(Long id) {
        return foodCategoryRepository.findById(id);
    }
}
