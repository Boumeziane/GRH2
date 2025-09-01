package com.rh.grh.repository;

import com.rh.grh.entity.Leave;
import com.rh.grh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    // Liste des congés d'un employé
    List<Leave> findByEmployee(Employee employee);

    // Congés par statut
    List<Leave> findByStatus(String status);

    // Congés d'un employé dans une période
    List<Leave> findByEmployeeAndStartDateBetween(Employee employee, LocalDate start, LocalDate end);

    List<Leave> findByEmployeeAndEndDateBetween(Employee employee, LocalDate start, LocalDate end);
}
