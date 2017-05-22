package com.alex.cloud.dao;

import com.alex.cloud.model.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")
@Repository
public interface FileDao extends CrudRepository<File, Long>{

    @Query("select f from File f where f.user.id=:#{principal.id}")
    List<File> findAll();
}