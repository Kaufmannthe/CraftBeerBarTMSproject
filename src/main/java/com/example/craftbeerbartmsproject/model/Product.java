package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    private String producer;
    private String name;
    private float weight;
    private float strength;
    private float density;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;
}
