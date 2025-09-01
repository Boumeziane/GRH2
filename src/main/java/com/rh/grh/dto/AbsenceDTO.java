package com.rh.grh.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {
    private Long id;
    private LocalDate date;
    private String motif;
    private boolean justifiee;

    private Long employeeId; // référence simple
}
