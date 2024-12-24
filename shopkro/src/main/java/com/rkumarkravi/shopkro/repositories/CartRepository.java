package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByBuyerId(Long buyerId);
}