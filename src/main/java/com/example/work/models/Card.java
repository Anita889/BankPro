package com.example.work.models;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String cardPassword;
    private Long pincode;
    private Long account;
    @CreationTimestamp
    private Timestamp start;
    @UpdateTimestamp
    private Timestamp finish;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

