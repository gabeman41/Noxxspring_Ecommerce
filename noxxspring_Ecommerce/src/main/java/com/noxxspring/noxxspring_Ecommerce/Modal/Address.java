package com.noxxspring.noxxspring_Ecommerce.Modal;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
