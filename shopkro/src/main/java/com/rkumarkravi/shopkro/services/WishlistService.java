package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Wishlist;
import com.rkumarkravi.shopkro.entities.Product;
import com.rkumarkravi.shopkro.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist getWishlistByBuyerId(Long buyerId) {
        return wishlistRepository.findByBuyerId(buyerId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for buyerId: " + buyerId));
    }

    public Wishlist addProductToWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public void removeProductFromWishlist(Long wishlistId, Long productId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found with ID: " + wishlistId));
        wishlist.getProducts().removeIf(product -> product.getId().equals(productId));
        wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found with ID: " + wishlistId));
        wishlistRepository.delete(wishlist);
    }
}
