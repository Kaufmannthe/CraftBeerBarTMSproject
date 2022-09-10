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
    private int id;
    private int price;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;
    private String name;
    private float weight;
    private float strength;
    private float density;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCreated;
}
