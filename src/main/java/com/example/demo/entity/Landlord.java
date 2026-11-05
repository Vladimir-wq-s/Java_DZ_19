package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "landlords")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phone;


    @Column(nullable = false)
    private int rentedApartmentsCount = 0;


    @Column(nullable = false)
    private boolean archived = false;
}