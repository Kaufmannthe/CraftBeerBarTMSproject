package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private double price;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    @NotNull
    private Producer producer;
    @NotNull
    private String name;
    @NotNull
    private double weight;
    @NotNull
    private double strength;
    @NotNull
    private double density;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private ProductType type;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCreated;
    private String picture;
    private double rating;

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
