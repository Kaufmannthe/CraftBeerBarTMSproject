package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int price;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;
    private String name;
    private double weight;
    private double strength;
    private double density;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCreated;
    private String picture;

    public Product(long id, int price, Producer producer, String name, double weight, double strength, double density,
                   ProductType type, LocalDate dataCreated) {
        this.id = id;
        this.price = price;
        this.producer = producer;
        this.name = name;
        this.weight = weight;
        this.strength = strength;
        this.density = density;
        this.type = type;
        this.dataCreated = dataCreated;
    }
}
