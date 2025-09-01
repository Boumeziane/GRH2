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
public class Offer {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titre;
        private String description;
        private LocalDate datePublication;

        @OneToMany(mappedBy = "offer")
        private List<Candidature> candidatures;


}
