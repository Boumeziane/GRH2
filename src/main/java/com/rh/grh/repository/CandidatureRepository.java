package com.rh.grh.repository;

import com.rh.grh.entity.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

    //  récupérer toutes les candidatures pour une offre spécifique
    List<Candidature> findByOfferId(Long offerId);
}
