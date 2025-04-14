package com.korit.dreampath_back.service;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    @Value("${user.dir}")
    private String rootPath;

    public String saveFile(String path, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        String newFilename = null;
        try {
            String originalFilename = file.getOriginalFilename();
            newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFilename;

            File newFilePath = new File(rootPath + "/" + path);
            if (!newFilePath.exists()) {
                newFilePath.mkdirs();;
            }

            File newFile = new File(rootPath + "/" + path + "/" + newFilename);
            file.transferTo(newFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFilename;
    }

    public void deleteFile(String path) {

        File file = new File(rootPath + "/" + path);

        if (file.exists()) {
            file.delete();
        }
    }


}
