package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.entities.Cart;
import com.rkumarkravi.shopkro.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{buyerId}")
    public Cart getCartByBuyerId(@PathVariable Long buyerId) {
        return cartService.getCartByBuyerId(buyerId);
    }

    @PostMapping("/add")
    public Cart addProductToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @PutMapping("/{cartId}/update")
    public Cart updateCart(@PathVariable Long cartId, @RequestBody Cart updatedCart) {
        return cartService.updateCart(cartId, updatedCart);
    }

    @DeleteMapping("/{cartId}")
    public String deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return "Cart deleted successfully!";
    }
}
