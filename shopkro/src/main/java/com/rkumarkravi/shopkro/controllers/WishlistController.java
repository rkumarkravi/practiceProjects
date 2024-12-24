package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.entities.Wishlist;
import com.rkumarkravi.shopkro.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/{buyerId}")
    public Wishlist getWishlistByBuyerId(@PathVariable Long buyerId) {
        return wishlistService.getWishlistByBuyerId(buyerId);
    }

    @PostMapping("/add")
    public Wishlist addProductToWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.addProductToWishlist(wishlist);
    }

    @DeleteMapping("/{wishlistId}/product/{productId}")
    public String removeProductFromWishlist(@PathVariable Long wishlistId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(wishlistId, productId);
        return "Product removed from wishlist successfully!";
    }

    @DeleteMapping("/{wishlistId}")
    public String deleteWishlist(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
        return "Wishlist deleted successfully!";
    }
}
