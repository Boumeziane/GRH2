package com.rh.grh.entity;

import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type; // CV, contrat, etc.
    private String url;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
