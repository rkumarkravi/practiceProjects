package com.rkumarkravi.shopkro.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rkumarkravi.shopkro.services.specs.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3StorageService implements StorageService {

    @Autowired(required = false)
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));

        // Return the public URL of the file
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    @Override
    public void deleteFile(String filePath) {
        String fileName = extractFileNameFromUrl(filePath);
        amazonS3.deleteObject(bucketName, fileName);
    }

    @Override
    public String getFileUrl(String filePath) {
        // For S3, return the URL as is
        return filePath;
    }

    private String extractFileNameFromUrl(String fileUrl) {
        // Extract the file name from the S3 file URL
        return fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    }
}


