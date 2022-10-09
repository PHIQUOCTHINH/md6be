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

//    @Override
//    public List<Food> findFoodsByMerchantId(Long id) {
//        return foodRepository.findFoodsByMerchantId(id);
//    }

    @Override
    public List<Food> findAllByUserId(Long id) {
        return foodRepository.findAllByUserId(id);
    }

    @Override
    public List<Food> findFood(String f, Long id) {
        return foodRepository.findFood("%"+f+"%",id);
    }

    @Override
    public List<Food> findFoodByLikeName(Long id, String name) {
        return foodRepository.findByLikeName(id,"%"+name+"%");
    }

    @Override
    public List<Food> findAllFoodByStatus() {
        return foodRepository.findAllFoodByStatus();
    }

    @Override
    public List<Food> findAllByFoodCategoryAndAndMerchantIsActive(Long id) {
        return foodRepository.findAllByFoodCategoryAndAndMerchantIsActive(id);
    }

    @Override
    public List<Food> findAllByLikeName(String name) {
        return foodRepository.findAllByLikeName(name);
    }

    public List<Food> findFoodByMerchant(Merchant merchant) {
        return foodRepository.findFoodByMerchant(merchant);
    }
}