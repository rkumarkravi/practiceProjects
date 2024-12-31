package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Query("select p from ProductImage p where p.product.id = ?1")
    List<ProductImage> findByProductId(Long id);

}