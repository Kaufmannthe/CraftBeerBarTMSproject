package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private boolean isActive;

    private int age;

    private String address;
    private String gender;

    private String phoneNumber;

    private String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp()
    @Temporal(TemporalType.DATE)
    private Date dataCreated;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @Size(max = 3)
    private Set<Roles> role;

    private String picture;


    public User(long id, String firstName, String lastName, String login, String password, boolean isActive, int age,
                String address, String gender, String phoneNumber, String email, Date dataCreated, Set<Roles> role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.isActive = isActive;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dataCreated = dataCreated;
        this.role = role;
    }
}
