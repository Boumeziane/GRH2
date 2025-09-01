package com.rh.grh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private String message;
    private LocalDateTime dateEnvoi;
    private Boolean lue;
    private Long employeeId; // référence à l'employé
}
