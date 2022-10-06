package com.example.md6be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CartDetail> cartDetails;

}
