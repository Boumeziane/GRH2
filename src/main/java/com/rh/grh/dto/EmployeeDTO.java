package com.rh.grh.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String poste;
    private LocalDate dateEmbauche;
    private  String department;

    // On expose uniquement l'ID du compte lié (et pas tout l'objet Account)
    private Long accountId;

    // Optionnel : listes d'IDs des absences et congés pour éviter de charger tout
    private List<Long> leaveIds;
    private List<Long> absenceIds;
}
