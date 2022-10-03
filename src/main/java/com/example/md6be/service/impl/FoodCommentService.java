package com.example.md6be.service.impl;


import com.example.md6be.model.FoodComment;
import com.example.md6be.repository.IFoodCommentRepository;
import com.example.md6be.service.IFoodCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCommentService implements IFoodCommentService {
    @Autowired
    IFoodCommentRepository iFoodCommentRepository;
    public FoodComment save(FoodComment foodComment) {
        return iFoodCommentRepository.save(foodComment);
    }
}
