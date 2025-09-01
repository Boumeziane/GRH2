package com.rh.grh.controller;

import com.rh.grh.dto.AbsenceDTO;
import com.rh.grh.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/absences")
@RequiredArgsConstructor
public class AbsenceController {

    private final AbsenceService absenceService;

    // ✅ Créer une absence
    @PostMapping
    public ResponseEntity<AbsenceDTO> createAbsence(@RequestBody AbsenceDTO dto) {
        AbsenceDTO created = absenceService.createAbsence(dto);
        return ResponseEntity.ok(created);
    }

    // ✅ Mettre à jour une absence
    @PutMapping("/{id}")
    public ResponseEntity<AbsenceDTO> updateAbsence(@PathVariable Long id,
                                                    @RequestBody AbsenceDTO dto) {
        AbsenceDTO updated = absenceService.updateAbsence(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ✅ Supprimer une absence
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
        absenceService.deleteAbsence(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Récupérer une absence par son ID
    @GetMapping("/{id}")
    public ResponseEntity<AbsenceDTO> getAbsenceById(@PathVariable Long id) {
        AbsenceDTO dto = absenceService.getAbsenceById(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Récupérer toutes les absences
    @GetMapping
    public ResponseEntity<List<AbsenceDTO>> getAllAbsences() {
        List<AbsenceDTO> list = absenceService.getAllAbsences();
        return ResponseEntity.ok(list);
    }

    // ✅ Récupérer les absences par employeeId
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesByEmployeeId(@PathVariable Long employeeId) {
        List<AbsenceDTO> list = absenceService.getAbsencesByEmployeeId(employeeId);
        return ResponseEntity.ok(list);
    }

    // ✅ Récupérer les absences par plage de dates
    @GetMapping("/date-range")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AbsenceDTO> list = absenceService.getAbsencesByDateRange(startDate, endDate);
        return ResponseEntity.ok(list);
    }

    // ✅ Récupérer les absences par motif
    @GetMapping("/motif")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesByMotif(@RequestParam String motif) {
        List<AbsenceDTO> list = absenceService.getAbsencesByMotif(motif);
        return ResponseEntity.ok(list);
    }

    // ✅ Récupérer les absences par justification
    @GetMapping("/justifiee")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesByJustifiee(@RequestParam boolean justifiee) {
        List<AbsenceDTO> list = absenceService.getAbsencesByJustifiee(justifiee);
        return ResponseEntity.ok(list);
    }
}
