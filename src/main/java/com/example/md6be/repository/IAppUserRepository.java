package com.example.md6be.repository;


import com.example.md6be.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    AppUser findAppUserById(Long id);
    AppUser findByPasswordAndUsername(String pass,String name);
    AppUser findByPassword(String pass);
}
