package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table( name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createAt;
    private double priceTotal;
    @ManyToOne
    private Customer customer;
    @Column(name = "is_Accept", columnDefinition = "boolean default false")
    private Boolean isAccept;
    @Column(name = "isPaid", columnDefinition = "boolean default false")
    private Boolean isPaid;

    @ManyToOne
    private Merchant merchant;
}
