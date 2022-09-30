package com.example.md6be.controller;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Role;
import com.example.md6be.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login-register")
public class LoginController {

    @Autowired
    IAppUserService appUserService;
    @GetMapping
    public ResponseEntity<List<AppUser>>findAll() {
        return new ResponseEntity<>(appUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AppUser>login(@RequestBody AppUser appUser) {
        AppUser appUser1 = appUserService.findByUserName(appUser.getUsername());
        if (appUser1!=null){
            for (Role role: appUser1.getRoles()) {
                if (role.getName().equals("ROLE_CUSTOMER")){
                    return new ResponseEntity<>(appUser1, HttpStatus.OK);
                }else if (role.getName().equals("ROLE_ADMIN")){
                    return new ResponseEntity<>(appUser1, HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(appUser1, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(appUser1, HttpStatus.OK);
        }else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

    }
}
