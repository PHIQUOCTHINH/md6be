package com.example.md6be.service;

import com.example.md6be.model.CartDetail;

import java.util.List;

public interface ICartDetailService {
    public CartDetail save(CartDetail cartDetail);
    List<CartDetail> getAll();

}
