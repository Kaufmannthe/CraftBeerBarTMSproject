package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private boolean isChecked;
    @CollectionTable(name = "producer", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "signatory")
    private String signatory;

}
