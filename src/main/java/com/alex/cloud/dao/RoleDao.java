package com.alex.cloud.dao;

import com.alex.cloud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alexandru_bobernac on 11/25/16.
 */
public interface RoleDao extends JpaRepository <Role, Long> {
    Role findByName(String name);
}
