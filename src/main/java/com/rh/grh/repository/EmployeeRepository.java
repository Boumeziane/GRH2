package com.rh.grh.repository;

import com.rh.grh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 🔹 Recherche basique
    Optional<Employee> findByEmail(String email);
    List<Employee> findByNom(String nom);
    List<Employee> findByPrenomContainingIgnoreCase(String prenom);

    // 🔹 Recherche par département / poste
    List<Employee> findByPoste(String poste);
    List<Employee> findByDepartment(String department);

    // 🔹 Recherche combinée
    List<Employee> findByDepartmentAndPoste(String department, String poste);

    // 🔹 Filtrer par date d’embauche
    List<Employee> findByDateEmbaucheAfter(LocalDate date);
    List<Employee> findByDateEmbaucheBetween(LocalDate start, LocalDate end);

    // 🔹 Statistiques RH
    long countByDepartment(String department);
    long countByPoste(String poste);

    // 🔹 Recherche avancée (multi-critères simples)
    List<Employee> findByNomAndDepartment(String nom, String department);

    // 🔹 Pour récupérer un employé par son compte
    Optional<Employee> findByAccountId(Long accountId);

    // ❌ Méthodes invalides (supprimées ou à implémenter si tu ajoutes les champs)
    // long countByActiveTrue();
    // long countByActiveFalse();
    // List<Employee> findByContractType(String contractType);
    // List<Employee> findByEndDateBefore(LocalDate date);
}
