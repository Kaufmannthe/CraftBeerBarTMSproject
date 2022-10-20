package com.example.craftbeerbartmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private String picture;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp()
    @Temporal(TemporalType.DATE)
    private Date dataCreated;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @CreationTimestamp()
    @Temporal(TemporalType.TIME)
    private Date time;

    private Roles role;


}
