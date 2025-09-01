package com.rh.grh.dto;

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
public class OfferDTO {
    private Long id;
    private String titre;
    private String description;
    private LocalDate datePublication;
    private List<CandidatureDTO> candidatures;
}
