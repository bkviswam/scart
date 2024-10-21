package com.intuit.scart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    private int stock;
}
