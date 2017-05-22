package com.alex.cloud.service;

import static com.alex.cloud.config.constants.RoleTypeConstants.*;
import com.alex.cloud.dao.RoleDao;
import com.alex.cloud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alexandru_bobernac on 12/13/16.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void save(String role) {
       if (role.toLowerCase().equals("admin")){

           roleDao.save(new Role(ADMIN));
       }
        else if (role.toLowerCase().equals("user")){

           roleDao.save(new Role(USER));
       }
    }
}
