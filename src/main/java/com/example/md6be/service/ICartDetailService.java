package com.example.md6be.service;

import com.example.md6be.model.CartDetail;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    public CartDetail save(CartDetail cartDetail);
    List<CartDetail> getAll();
    List<CartDetail> findAllByUserId( Long id);

    Optional<CartDetail> findById(Long id);

    void delete(Long id);
    List<CartDetail> findAllByCartId(Long id);

}