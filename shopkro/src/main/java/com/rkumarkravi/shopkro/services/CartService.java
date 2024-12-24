package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.Cart;
import com.rkumarkravi.shopkro.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByBuyerId(Long buyerId) {
        return cartRepository.findByBuyerId(buyerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for buyerId: " + buyerId));
    }

    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long cartId, Cart updatedCart) {
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));
        existingCart.setTotalPrice(updatedCart.getTotalPrice());
        existingCart.setDiscountApplied(updatedCart.getDiscountApplied());
        existingCart.setCartItems(updatedCart.getCartItems());
        return cartRepository.save(existingCart);
    }

    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));
        cartRepository.delete(cart);
    }
}
