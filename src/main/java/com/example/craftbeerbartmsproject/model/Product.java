package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
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
    @CreationTimestamp()
    @Temporal(TemporalType.DATE)
    private Date dataCreated;

    private String picture;

    private double rating;

    public Product(long id, int price, Producer producer, String name, double weight, double strength, double density,
                   ProductType type, Date dataCreated) {
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
