package com.example.md6be.service.impl;

import com.example.md6be.repository.IMerchantRepository;
import com.example.md6be.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService implements IMerchantService {
    @Autowired
    IMerchantRepository merchantRepository;
}
