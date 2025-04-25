package com.repsy.controller;

import com.repsy.entity.Package;
import com.repsy.repository.PackageRepository;
import com.repsy.service.storage.StorageService;
import com.repsy.util.JsonUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DeploymentController {

    private final StorageService storageService;
    private final PackageRepository packageRepository;

    @PostMapping("/{packageName}/{version}")
    @Transactional
    public ResponseEntity<String> uploadPackage(
            @PathVariable String packageName,
            @PathVariable String version,
            @RequestParam("package") MultipartFile packageFile,
            @RequestParam("meta") MultipartFile metaFile
    ) {
        try {
            Map<String, Object> metadata = JsonUtil.parseMetaJson(metaFile);

            String basePath = packageName + "/" + version + "/";
            storageService.upload(basePath + "package.rep", packageFile);
            storageService.upload(basePath + "meta.json", metaFile);

            Package aPackage = new Package();
            aPackage.setPackageName(packageName);
            aPackage.setVersion(version);
            aPackage.setAuthor((String) metadata.get("author"));
            aPackage.setUploadDate(LocalDateTime.now());

            packageRepository.save(aPackage);

            return ResponseEntity.status(HttpStatus.CREATED).body("Package uploaded successfully");
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid meta.json file.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload package.");
        }
    }

}
