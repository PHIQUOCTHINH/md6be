package com.example.md6be.service.impl;


import com.example.md6be.model.Food;
import com.example.md6be.model.Merchant;
import com.example.md6be.repository.IFoodRepository;
import com.example.md6be.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService implements IFoodService {
    @Autowired
    IFoodRepository foodRepository;

    @Override
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public void save(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public List<Food> findFoodsByMerchantId(Long id) {
        return foodRepository.findFoodsByMerchantId(id);
    }

    public List<Food> findFoodByMerchant(Merchant merchant) {
        return foodRepository.findFoodByMerchant(merchant);
    }
}
