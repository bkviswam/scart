package com.intuit.scart.controller;


import com.intuit.scart.entity.Cart;
import com.intuit.scart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity){
        Cart cart = cartService.addProductToCart(cartId, productId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/remove/{itemId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long itemId){
        Cart cart = cartService.removeProductFromCart(cartId, itemId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId){
        Cart cart = cartService.getCart(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Cart> clearCart(@PathVariable Long cartId){
        cartService.clearCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
