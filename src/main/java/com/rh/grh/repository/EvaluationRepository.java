package com.rh.grh.repository;

import com.rh.grh.entity.Evaluation;
import com.rh.grh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // Récupérer toutes les évaluations d'un employé
    List<Evaluation> findByEmployee(Employee employee);

    // Récupérer les évaluations dans une période donnée
    List<Evaluation> findByDateBetween(LocalDate start, LocalDate end);

    // Récupérer les évaluations d'un employé dans une période
    List<Evaluation> findByEmployeeAndDateBetween(Employee employee, LocalDate start, LocalDate end);
}
