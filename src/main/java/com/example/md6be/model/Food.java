package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Lob
    private String image;
    private Long quantityStorage;
    private String description;
    private double price;
    @Column (columnDefinition = "BIGINT default 0")
    private Long sold;
    @Column(name = "is_Empty", columnDefinition = "boolean default false")
    private Boolean isEmpty;
    @ManyToOne
    private FoodCategory foodCategory;
    @ManyToOne
    private Merchant merchant;
}
