package com.rh.grh.repository;

import com.rh.grh.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    // Rechercher des offres par titre (contient, ignore case)
    List<Offer> findByTitreContainingIgnoreCase(String titre);

    // Rechercher des offres publiées à une date précise
    List<Offer> findByDatePublication(LocalDate datePublication);

    // Rechercher les offres publiées entre deux dates
    List<Offer> findByDatePublicationBetween(LocalDate startDate, LocalDate endDate);
}
