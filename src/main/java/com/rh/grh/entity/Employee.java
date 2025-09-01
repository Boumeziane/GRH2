package com.rh.grh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nom;
        private String prenom;
        private String email;
        private String poste;
        private LocalDate dateEmbauche;
        private  String department;


        @OneToOne
        @JoinColumn(name = "account_id")
        private Account account;


        @OneToMany(mappedBy = "employee")
        private List<Leave> leaves;


        @OneToMany(mappedBy = "employee")
        private List<Absence> absences;




}
