package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.entities.CartItem;
import com.rkumarkravi.shopkro.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{cartId}")
    public List<CartItem> getCartItemsByCartId(@PathVariable Long cartId) {
        return cartItemService.getCartItemsByCartId(cartId);
    }

    @PostMapping("/add")
    public CartItem addCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.addCartItem(cartItem);
    }

    @PutMapping("/{cartItemId}/update")
    public CartItem updateCartItem(@PathVariable Long cartItemId, @RequestBody CartItem updatedCartItem) {
        return cartItemService.updateCartItem(cartItemId, updatedCartItem);
    }

    @DeleteMapping("/{cartItemId}")
    public String deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return "Cart item deleted successfully!";
    }
}
