package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class FoodComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date creatAt;
    private String message;
    private int rating;
    @ManyToOne
    private Food food;
    @ManyToOne
    private Customer customer;
}
