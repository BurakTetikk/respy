package com.repsy.controller;

import com.repsy.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DownloadController {

    private final StorageService storageService;

    @GetMapping("/{packageName}/{version}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName
    ){
        String path = packageName + "/" + version + "/" + fileName;

        Resource file = storageService.download(path);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }


}
