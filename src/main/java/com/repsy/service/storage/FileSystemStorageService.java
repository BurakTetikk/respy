package com.repsy.service.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service("fileSystemStorageService")
@RequiredArgsConstructor
public class FileSystemStorageService implements StorageService{

    @Value("${storage.file-system.path}")
    private String rootPath;

    @Override
    public void upload(String path, MultipartFile file) {

        try {
            Path fullPath = Paths.get(rootPath, path).normalize();
            Files.createDirectories(fullPath.getParent());
            file.transferTo(fullPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), e);
        }

    }

    @Override
    public Resource download(String path) {
        try {
            Path fullPath = Paths.get(rootPath, path).normalize();
            if (!Files.exists(fullPath)) {
                throw new RuntimeException("File not found: " + path);
            }

            return new FileSystemResource(fullPath.toFile());

        } catch (Exception e){
            throw new RuntimeException("Failed to read file: " + path, e);
        }
    }
}
