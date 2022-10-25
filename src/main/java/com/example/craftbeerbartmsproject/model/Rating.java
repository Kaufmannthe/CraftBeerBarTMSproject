package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rating;

    @OneToOne(cascade = CascadeType.MERGE)
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Rating(int rating, Product productId, User user) {
        this.rating = rating;
        this.product = productId;
        this.user = user;
    }
}
