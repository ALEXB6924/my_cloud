package com.alex.cloud.service;

import com.alex.cloud.model.File;
import com.alex.cloud.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<File> findAll();
    void save(File file, MultipartFile multipartFile, User user) throws IOException;
    File findOne(Long id);
}
