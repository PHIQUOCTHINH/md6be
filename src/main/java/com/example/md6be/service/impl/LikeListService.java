package com.example.md6be.service.impl;


import com.example.md6be.repository.ILikeListRepository;
import com.example.md6be.service.ILikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeListService implements ILikeListService {
    @Autowired
    ILikeListRepository likeListRepository;
}
