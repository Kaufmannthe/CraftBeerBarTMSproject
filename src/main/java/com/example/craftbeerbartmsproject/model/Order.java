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
    private Producer producerId;

    @OneToOne
    private User userId;

}
