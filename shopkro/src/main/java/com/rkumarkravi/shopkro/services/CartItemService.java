package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.CartItem;
import com.rkumarkravi.shopkro.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long cartItemId, CartItem updatedCartItem) {
        CartItem existingCartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found with ID: " + cartItemId));
        existingCartItem.setQuantity(updatedCartItem.getQuantity());
        existingCartItem.setPricePerUnit(updatedCartItem.getPricePerUnit());
        return cartItemRepository.save(existingCartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found with ID: " + cartItemId));
        cartItemRepository.delete(cartItem);
    }
}
