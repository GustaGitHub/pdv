package com.logistica.pdv.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String Name;

    @OneToMany(mappedBy = "Seller")
    private List<Product> products;
}
