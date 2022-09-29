package com.example.md6be.service.impl;


import com.example.md6be.repository.IFoodRepository;
import com.example.md6be.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService implements IFoodService {
    @Autowired
    IFoodRepository foodRepository;
}
