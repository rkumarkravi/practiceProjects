package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}