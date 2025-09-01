package com.rh.grh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String cvUrl;
    private Long offerId; // référence à l'offre
}
