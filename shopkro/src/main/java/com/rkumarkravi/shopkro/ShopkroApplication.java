package com.rkumarkravi.shopkro;

import com.rkumarkravi.shopkro.repositories.ProductRepository;
import com.rkumarkravi.shopkro.repositories.SellerRepository;
import com.rkumarkravi.shopkro.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShopkroApplication {
    @Autowired
    MailService mailService;

    public static void main(String[] args) {
        SpringApplication.run(ShopkroApplication.class, args);
    }
}
