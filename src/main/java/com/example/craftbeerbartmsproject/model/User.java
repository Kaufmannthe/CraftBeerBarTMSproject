package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
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
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @Size(max = 3)
    private Set<Roles> role;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_list")
    private List<Contacts> contactsList;

/*    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Cart cart;*/

    private String picture;


    public User(long id, String firstName, String lastName, String login, String password, boolean isActive, int age,
                String address, String gender, String phoneNumber, String email, Date dataCreated, Set<Roles> role,
                List<Contacts> contactsList) {
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

    }
}
