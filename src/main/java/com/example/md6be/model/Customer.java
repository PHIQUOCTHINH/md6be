package com.example.md6be.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Lob //chú thích cùng với @Column để nói rằng cột đó có kiểu BLOB
    private String avatar;
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @Column(name = "is_Accept", columnDefinition = "boolean default false")
    private Boolean isAccept;

    @Column(name = "is_Active", columnDefinition = "boolean default true")
    private Boolean isActive;
}
