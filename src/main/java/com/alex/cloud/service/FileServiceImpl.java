package com.alex.cloud.service;

import com.alex.cloud.dao.FileDao;
import com.alex.cloud.model.File;
import com.alex.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileDao fileDao;

    @Override
    public List<File> findAll() {
        return fileDao.findAll();
    }

    @Override
    public void save(File file, MultipartFile multipartFile, User user) throws IOException {
        file.setName(multipartFile.getOriginalFilename());
        file.setBytes(multipartFile.getBytes());
        file.setUser(user);
        fileDao.save(file);
    }

    @Override
    public File findOne(Long id) {
        return fileDao.findOne(id);

    }
}
