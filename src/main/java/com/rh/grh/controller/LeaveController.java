package com.rh.grh.controller;

import com.rh.grh.dto.LeaveDTO;
import com.rh.grh.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    // Ajouter un congé
    @PostMapping
    public LeaveDTO createLeave(@RequestBody LeaveDTO dto) {
        return leaveService.createLeave(dto);
    }

    // Modifier un congé
    @PutMapping("/{id}")
    public LeaveDTO updateLeave(@PathVariable Long id, @RequestBody LeaveDTO dto) {
        return leaveService.updateLeave(id, dto);
    }

    // Supprimer un congé
    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
    }

    // Obtenir un congé par ID
    @GetMapping("/{id}")
    public LeaveDTO getLeaveById(@PathVariable Long id) {
        return leaveService.getLeaveById(id);
    }

    // Liste de tous les congés
    @GetMapping
    public List<LeaveDTO> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    // Liste des congés d’un employé
    @GetMapping("/employee/{employeeId}")
    public List<LeaveDTO> getLeavesByEmployee(@PathVariable Long employeeId) {
        return leaveService.getLeavesByEmployee(employeeId);
    }

    // Liste des congés par statut (ex: "APPROVED", "PENDING", "REJECTED")
    @GetMapping("/status/{status}")
    public List<LeaveDTO> getLeavesByStatus(@PathVariable String status) {
        return leaveService.getLeavesByStatus(status);
    }

    // Liste des congés d’un employé dans une plage de dates
    @GetMapping("/employee/{employeeId}/daterange")
    public List<LeaveDTO> getLeavesByEmployeeAndDateRange(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return leaveService.getLeavesByEmployeeAndDateRange(employeeId, start, end);
    }
}
