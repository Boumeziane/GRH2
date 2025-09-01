package com.rh.grh.service;

import com.rh.grh.dto.EvaluationDTO;

import java.time.LocalDate;
import java.util.List;

public interface EvaluationService {

    // Créer une évaluation
    EvaluationDTO createEvaluation(EvaluationDTO dto);

    // Mettre à jour une évaluation
    EvaluationDTO updateEvaluation(Long id, EvaluationDTO dto);

    // Supprimer une évaluation
    void deleteEvaluation(Long id);

    // Récupérer une évaluation par id
    EvaluationDTO getEvaluationById(Long id);

    // Liste des évaluations d’un employé
    List<EvaluationDTO> getEvaluationsByEmployee(Long employeeId);

    // Liste des évaluations dans une période
    List<EvaluationDTO> getEvaluationsByDateRange(LocalDate start, LocalDate end);

    // Liste des évaluations d’un employé dans une période
    List<EvaluationDTO> getEvaluationsByEmployeeAndDateRange(Long employeeId, LocalDate start, LocalDate end);

    // Liste de toutes les évaluations
    List<EvaluationDTO> getAllEvaluations();
}
