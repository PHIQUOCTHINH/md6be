package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String content;
    @OneToOne
    private Merchant merchant;
    @OneToOne
    private Customer customer;
}
