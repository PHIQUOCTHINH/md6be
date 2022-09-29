package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cartdetail")
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Food food;
    @ManyToOne
    private Merchant merchant;
    private double quantity;
    private double totalPrice;
}
