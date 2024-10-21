package com.intuit.scart.repository;

import com.intuit.scart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart, Long> {
}
