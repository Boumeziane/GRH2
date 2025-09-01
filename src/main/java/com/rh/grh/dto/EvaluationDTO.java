package com.rh.grh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    private Long id;
    private LocalDate date;
    private String commentaire;
    private Integer note;
    private Long employeeId; // référence à l'employé
}
