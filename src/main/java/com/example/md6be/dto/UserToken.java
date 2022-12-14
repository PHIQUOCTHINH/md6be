package com.example.md6be.dto;


import com.example.md6be.model.Role;

import java.util.Set;

public class UserToken {
    private long id;
    private String username;
    private String token;
    private Set<Role> roles;

    public UserToken() {
    }

    public UserToken(long id, String username, String token, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
