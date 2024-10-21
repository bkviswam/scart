package com.intuit.scart.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "CartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity;
}
