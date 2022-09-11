package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "producer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String specialization;
    private String description;
    private String numberOfSignatory;
    private float rating;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "signatory_id")
    private User signatory;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCreated;

    private String picture;

    public Producer(long id, String name, String address, String phoneNumber, String specialization,
                    String description, String numberOfSignatory, float rating, User signatory, LocalDate dataCreated) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.description = description;
        this.numberOfSignatory = numberOfSignatory;
        this.rating = rating;
        this.signatory = signatory;
        this.dataCreated = dataCreated;
    }
}
