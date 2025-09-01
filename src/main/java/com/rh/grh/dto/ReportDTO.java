package com.rh.grh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private String employeeName;
    private String employeeEmail;
    private String poste;
    private int totalAbsences;
    private int totalLeaves;
    private double averageEvaluation;
}
