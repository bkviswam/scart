package com.intuit.scart.service;


import com.intuit.scart.entity.Cart;
import com.intuit.scart.entity.CartItem;
import com.intuit.scart.entity.Product;
import com.intuit.scart.repository.CartRepository;
import com.intuit.scart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addProductToCart(Long cartId, Long productId, int quantity){
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingCartItemOpt = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst();

        if (existingCartItemOpt.isPresent()) {
            CartItem existingCartItem = existingCartItemOpt.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);

            cart.getItems().add(cartItem);
        }

        cart.setTotalAmount(cart.getItems().stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum());
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long cartId, Long itemId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().removeIf(cartItem -> cartItem.getId().equals(itemId));
        cart.setTotalAmount(cart.getItems().stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum());
        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId){
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void clearCart(Long cartId){
        cartRepository.deleteById(cartId);
    }


}
