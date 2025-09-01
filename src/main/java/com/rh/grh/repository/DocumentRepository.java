package com.rh.grh.repository;

import com.rh.grh.entity.Document;
import com.rh.grh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    // Récupérer tous les documents d'un employé
    List<Document> findByEmployee(Employee employee);

    // Rechercher des documents par type (ex: CV, contrat)
    List<Document> findByType(String type);

    // Rechercher par nom de document (contient, ignore case)
    List<Document> findByNomContainingIgnoreCase(String nom);
}
