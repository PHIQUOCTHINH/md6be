package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    private double priceTotal;
    @ManyToOne
    private Customer customer;
    @Column(name = "is_Accept", columnDefinition = "boolean default false")
    private Boolean isAccept;
    @ManyToOne
    private Merchant merchant;
}
