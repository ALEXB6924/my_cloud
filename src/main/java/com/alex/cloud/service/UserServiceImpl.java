package com.alex.cloud.service;

import static com.alex.cloud.config.constants.RoleTypeConstants.*;
import com.alex.cloud.dao.RoleDao;
import com.alex.cloud.dao.UserDao;
import com.alex.cloud.model.Role;
import com.alex.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void save(String username, String password, String role, boolean enabled) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (role.toLowerCase().equals("admin")){

            userDao.save(new User(username, passwordEncoder.encode(password), enabled, roleDao.findByName(ADMIN)));
        }
        else {
            userDao.save(new User(username, passwordEncoder.encode(password), enabled, roleDao.findByName(USER)));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);
        if(user == null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
