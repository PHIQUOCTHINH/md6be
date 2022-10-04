package com.example.md6be.service;

import com.example.md6be.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface IAppUserService {
    AppUser findByUserName(String username);
    public Optional<AppUser> findByUserId(Long id);

    List<AppUser> findAll();

    AppUser findAppUserByPasswordAndUsername(String password, String username);
    AppUser findAppUserByPassword(String pass);
    void save(AppUser appUser);
}
