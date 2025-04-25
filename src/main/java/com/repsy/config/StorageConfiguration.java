package com.repsy.config;

import com.repsy.service.storage.FileSystemStorageService;
import com.repsy.service.storage.ObjectStorageService;
import com.repsy.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StorageConfiguration {

    private final FileSystemStorageService fileSystemStorageService;
    private final ObjectStorageService objectStorageService;

    @Value("${storage.strategy:file-system}")
    private String storageStrategy;

    @Bean
    public StorageService storageService() {
        if ("object-storage".equalsIgnoreCase(storageStrategy)) {
            return objectStorageService;
        }else {
            return fileSystemStorageService;
        }
    }

}
