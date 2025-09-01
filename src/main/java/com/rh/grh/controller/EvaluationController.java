package com.rh.grh.controller;

import com.rh.grh.dto.EvaluationDTO;
import com.rh.grh.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    // 🔹 Ajouter une évaluation
    @PostMapping
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO dto) {
        return ResponseEntity.ok(evaluationService.createEvaluation(dto));
    }

    // 🔹 Modifier une évaluation
    @PutMapping("/{id}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDTO dto) {
        return ResponseEntity.ok(evaluationService.updateEvaluation(id, dto));
    }

    // 🔹 Supprimer une évaluation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Récupérer une évaluation par ID
    @GetMapping("/{id}")
    public ResponseEntity<EvaluationDTO> getEvaluationById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getEvaluationById(id));
    }

    // 🔹 Récupérer toutes les évaluations
    @GetMapping
    public ResponseEntity<List<EvaluationDTO>> getAllEvaluations() {
        return ResponseEntity.ok(evaluationService.getAllEvaluations());
    }

    // 🔹 Récupérer les évaluations d’un employé
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EvaluationDTO>> getEvaluationsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsByEmployee(employeeId));
    }

    // 🔹 Récupérer les évaluations entre deux dates
    @GetMapping("/date-range")
    public ResponseEntity<List<EvaluationDTO>> getEvaluationsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(evaluationService.getEvaluationsByDateRange(start, end));
    }

    // 🔹 Récupérer les évaluations d’un employé entre deux dates
    @GetMapping("/employee/{employeeId}/date-range")
    public ResponseEntity<List<EvaluationDTO>> getEvaluationsByEmployeeAndDateRange(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(evaluationService.getEvaluationsByEmployeeAndDateRange(employeeId, start, end));
    }
}
