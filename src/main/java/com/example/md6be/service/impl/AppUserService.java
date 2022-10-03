package com.example.md6be.service.impl;


import com.example.md6be.model.AppUser;
import com.example.md6be.repository.IAppUserRepository;
import com.example.md6be.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {
    @Autowired
    IAppUserRepository appUserRepository;
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
    public AppUser findByUserName(String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        return appUser;
    }
}
