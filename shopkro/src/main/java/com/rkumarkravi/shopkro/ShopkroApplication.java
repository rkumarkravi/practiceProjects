package com.rkumarkravi.shopkro;

import com.rkumarkravi.shopkro.repositories.ProductRepository;
import com.rkumarkravi.shopkro.repositories.SellerRepository;
import com.rkumarkravi.shopkro.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopkroApplication {
    @Autowired
    MailService mailService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShopkroApplication.class, args);
    }
}
