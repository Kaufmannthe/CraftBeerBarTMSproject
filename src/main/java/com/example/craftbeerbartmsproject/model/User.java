package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
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
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private boolean isActive;
    @NotNull
    private int age;
    @NotNull
    private String address;
    private String gender;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCreated;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    @Size(max = 3)
    private Set<Roles> role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_list")
    private List<Contacts> contactsList;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Cart cart;

    private String picture;

    public User(long id, String firstName, String lastName, String login, String password, boolean isActive, int age, String address,
                String gender, String phoneNumber, String email, LocalDate dataCreated, Set<Roles> role,
                List<Contacts> contactsList, Cart cart) {
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
        this.contactsList = contactsList;
        this.cart = cart;
    }
}
