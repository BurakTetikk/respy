package com.repsy.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void upload(String path, MultipartFile file);

    Resource download(String path);
}
