package com.example.md6be.service.impl;


import com.example.md6be.repository.IChatRepository;
import com.example.md6be.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService {
    @Autowired
    IChatRepository chatRepository;
}
