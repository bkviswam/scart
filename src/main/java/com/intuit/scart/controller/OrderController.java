package com.intuit.scart.controller;

import com.intuit.scart.entity.Order;
import com.intuit.scart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout/{cartId}")
    public ResponseEntity<Order>  checkout(@PathVariable Long cartId){
        Order order = orderService.checkOut(cartId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
