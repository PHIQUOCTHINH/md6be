package com.example.md6be.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table( name = "orders")
@Data
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createAt;
    @Column(name = "isAccept", columnDefinition = "boolean default false")
    private Boolean isAccept;
    private double priceTotal;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Merchant merchant;


}
