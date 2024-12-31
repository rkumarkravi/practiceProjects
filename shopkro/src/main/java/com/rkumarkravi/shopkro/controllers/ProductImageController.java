package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.entities.ProductImage;
import com.rkumarkravi.shopkro.services.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    // Upload Images
    @PostMapping("/{productId}/upload-images")
    public ResponseEntity<String> uploadProductImages(
            @PathVariable Long productId,
            @RequestParam("files") List<MultipartFile> files) {
        try {
            // Validate the number of files in the request
            if (files.size() < 1 || files.size() > 5) {
                return ResponseEntity.badRequest().body("You must upload between 1 and 5 images");
            }

            productImageService.uploadProductImages(productId, files);
            return ResponseEntity.ok("Images uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed: " + e.getMessage());
        }
    }

    // Fetch Images for a Product
    @GetMapping("/{productId}/images")
    public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable Long productId) {
        try {
            List<ProductImage> images = productImageService.getProductImages(productId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete Image by ID
    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<String> deleteProductImage(@PathVariable Long imageId) {
        try {
            productImageService.deleteProductImage(imageId);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image deletion failed: " + e.getMessage());
        }
    }
}

