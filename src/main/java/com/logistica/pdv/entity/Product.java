package com.logistica.pdv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    @JsonIgnore
    private Seller Seller;

    @Column(name = "DESCRIPTION", nullable = false, length = 160)
    private String Description;

    @Column(name = "PRICE", nullable = false, length = 20, scale = 2, precision = 20)
    private BigDecimal Price;

    @Column(name = "SALE_DATE", nullable = false)
    private LocalDate SaleDate;
}
