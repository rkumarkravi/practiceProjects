package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}