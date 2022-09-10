package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts")
public class Contacts {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private int age;
    private String address;
    private String gender;
    private String phoneNumber;
    private String email;
    @ManyToOne
    private User user;
}
