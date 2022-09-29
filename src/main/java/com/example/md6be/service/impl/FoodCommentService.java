package com.example.md6be.service.impl;


import com.example.md6be.repository.IFoodCommentRepository;
import com.example.md6be.service.IFoodCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCommentService implements IFoodCommentService {
    @Autowired
    IFoodCommentRepository iFoodCommentRepository;
}
