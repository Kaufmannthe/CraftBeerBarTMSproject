package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "producer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String specialization;
    private String description;
    private String numberOfSignatory;
    private float rating;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "signatory_id")
    private User signatory;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCreated;

    private String picture;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @Size(max = 3)
    private Set<Roles> role;


    public Producer(long id, String login, String password, String name, String address, String phoneNumber,
                    String specialization, String description, String numberOfSignatory, float rating, User signatory,
                    LocalDate dataCreated) {
        this.id = id;
        this.login = login;
        this.password = password;
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
