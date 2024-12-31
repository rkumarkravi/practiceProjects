package com.rkumarkravi.shopkro.configs;

import com.rkumarkravi.shopkro.services.FileSystemStorageService;
import com.rkumarkravi.shopkro.services.S3StorageService;
import com.rkumarkravi.shopkro.services.specs.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageServiceConfig {

    @Value("${storage.type}")
    private String storageType;

    private final FileSystemStorageService fileSystemStorageService;
    private final S3StorageService s3StorageService;

    public StorageServiceConfig(FileSystemStorageService fileSystemStorageService, S3StorageService s3StorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
        this.s3StorageService = s3StorageService;
    }

    @Bean
    public StorageService storageService() {
        if ("s3".equalsIgnoreCase(storageType)) {
            return s3StorageService;
        } else if ("filesystem".equalsIgnoreCase(storageType)) {
            return fileSystemStorageService;
        } else {
            throw new IllegalArgumentException("Invalid storage type: " + storageType);
        }
    }
}


