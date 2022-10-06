package com.example.md6be.service;

import com.example.md6be.model.Food;
import com.example.md6be.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IFoodService {
    Optional<Food> findById(Long id);

    void save(Food food);

    void delete(Long id);

    List<Food> findAll();
    List<Food> findFoodsByUserId(Long id);
    List<Food> findFood( String f);

    List<Food> findAllByMerchant(Merchant merchant);
}
