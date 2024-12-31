package com.rkumarkravi.shopkro.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {
    @Value("${product.image.storage.path:uploads/products}")
    private String storagePath;

    public String getStoragePath() {
        return storagePath;
    }
}

