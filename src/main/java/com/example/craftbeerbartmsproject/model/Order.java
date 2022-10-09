package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToOne
    private Producer producer;

    @OneToOne
    private Product product;

    @OneToOne
    private User user;

    private int amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(Producer producer, User user, int amount) {
        this.producer = producer;
        this.user = user;
        this.amount = amount;
    }
}
