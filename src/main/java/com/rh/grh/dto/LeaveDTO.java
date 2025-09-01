package com.rh.grh.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    private Long employeeId; // référence simple
}
