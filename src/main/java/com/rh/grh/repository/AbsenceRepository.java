package com.rh.grh.repository;

import com.rh.grh.entity.Absence;
import com.rh.grh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    // Liste des absences d'un employé
    List<Absence> findByEmployee(Employee employee);

    // Absences justifiées ou non
    List<Absence> findByJustifiee(boolean justifiee);

    // Absences d'un employé dans une période
    List<Absence> findByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);

    // Absences par motif
    List<Absence> findByMotifContainingIgnoreCase(String motif);
}
