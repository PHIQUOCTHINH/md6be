package com.example.md6be.service.impl;


import com.example.md6be.repository.IFoodCategoryRepository;
import com.example.md6be.service.IFoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCategoryService implements IFoodCategoryService {
    @Autowired
    IFoodCategoryRepository foodCategoryRepository;
}
