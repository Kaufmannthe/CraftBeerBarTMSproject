package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private Producer producer;

    @OneToOne(cascade = CascadeType.MERGE)
    private Product product;

    @OneToOne(cascade = CascadeType.MERGE)
    private User user;

    private int amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Courier courier;

    private boolean receivedByUser;

    private boolean deliveredAndPaidByUser;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp()
    @Temporal(TemporalType.DATE)
    private Date dataCreated;

    public Order(Producer producer, User user, int amount) {
        this.producer = producer;
        this.user = user;
        this.amount = amount;
    }
}
