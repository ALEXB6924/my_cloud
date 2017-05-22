package com.alex.cloud.service;

import com.alex.cloud.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User findByUsername(String username);
    void save(String username, String password, String role, boolean enabled);
}
