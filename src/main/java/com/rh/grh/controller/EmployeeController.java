package com.rh.grh.controller;

import com.rh.grh.dto.EmployeeDTO;
import com.rh.grh.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // ➕ Ajouter un employé
    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(employeeService.createEmployee(dto));
    }

    // ✏️ Modifier un employé
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    // ❌ Supprimer un employé
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // 📋 Récupérer tous les employés
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // 🔍 Récupérer un employé par son ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // 🔍 Récupérer un employé par l’ID du compte associé
    @GetMapping("/account/{accountId}")
    public ResponseEntity<EmployeeDTO> getByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(employeeService.getEmployeeByAccountId(accountId));
    }
}
