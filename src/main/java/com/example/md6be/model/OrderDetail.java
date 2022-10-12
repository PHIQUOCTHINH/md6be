package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Food food;
    @ManyToOne
    private Order order;
    private Long quantity;
    private double price;

}
