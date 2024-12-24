package com.rkumarkravi.shopkro.repositories;

import com.rkumarkravi.shopkro.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByBuyerId(Long buyerId);
}