package com.rh.grh.service;

import com.rh.grh.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    // Créer un nouvel employé
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    // Mettre à jour un employé
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    // Supprimer un employé
    void deleteEmployee(Long id);

    // Récupérer tous les employés
    List<EmployeeDTO> getAllEmployees();

    // Récupérer un employé par ID
    EmployeeDTO getEmployeeById(Long id);

    // Récupérer un employé par compte
    EmployeeDTO getEmployeeByAccountId(Long accountId);
}
