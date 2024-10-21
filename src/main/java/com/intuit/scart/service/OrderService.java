package com.intuit.scart.service;

import com.intuit.scart.entity.Cart;
import com.intuit.scart.entity.Order;
import com.intuit.scart.repository.CartRepository;
import com.intuit.scart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order checkOut(Long cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order  = new Order();
        order.setItems(cart.getItems());
        order.setTotalAmount(cart.getTotalAmount());

        cartRepository.deleteById(cartId);
        return orderRepository.save(order);
    }
}
