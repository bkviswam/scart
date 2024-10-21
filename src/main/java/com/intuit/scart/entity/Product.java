package com.intuit.scart.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;
}
