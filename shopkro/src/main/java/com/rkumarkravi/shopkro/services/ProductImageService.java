package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.configs.FileStorageConfig;
import com.rkumarkravi.shopkro.entities.Product;
import com.rkumarkravi.shopkro.entities.ProductImage;
import com.rkumarkravi.shopkro.repositories.ProductImageRepository;
import com.rkumarkravi.shopkro.repositories.ProductRepository;
import com.rkumarkravi.shopkro.services.specs.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final StorageService storageService; // Injected dynamically

    public List<ProductImage> uploadProductImages(Long productId, List<MultipartFile> imageFiles) throws IOException {
        // 1. Fetch Product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Validate Image Count
        List<ProductImage> existingImages = productImageRepository.findByProductId(productId);
        if (existingImages.size() + imageFiles.size() > 5) {
            throw new RuntimeException("Maximum of 5 images per product is allowed");
        }

        // 3. Upload Files and Save Records
        List<ProductImage> uploadedImages = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            // Upload file using the storage service
            String filePath = storageService.uploadFile(file);

            // Save Image Info to Database
            ProductImage productImage = new ProductImage();
            productImage.setImageUrl(filePath);
            productImage.setProduct(product);
            uploadedImages.add(productImageRepository.save(productImage));
        }

        return uploadedImages;
    }

    public List<ProductImage> getProductImages(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    public void deleteProductImage(Long imageId) {
        // 1. Fetch the ProductImage
        ProductImage productImage = productImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        // 2. Delete the file using the storage service
        storageService.deleteFile(productImage.getImageUrl());

        // 3. Delete the record from the database
        productImageRepository.deleteById(imageId);
    }
}


