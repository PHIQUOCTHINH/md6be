package com.example.md6be.service.impl;


import com.example.md6be.model.AppUser;
import com.example.md6be.repository.IAppUserRepository;
import com.example.md6be.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IAppUserService {
    @Autowired
    IAppUserRepository appUserRepository;


    @Override
    public AppUser findByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public Optional<AppUser> findByUserId(Long id) {
        return Optional.ofNullable(appUserRepository.findAppUserById(id));
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findAppUserByPasswordAndUsername(String pass, String username) {
        return appUserRepository.findByPasswordAndUsername(pass,username);
    }

    @Override
    public AppUser findAppUserByPassword(String pass) {
        return appUserRepository.findByPassword(pass);
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }
    AppUser findAppUserById(Long id){
        return appUserRepository.findAppUserById(id);
    }
}
