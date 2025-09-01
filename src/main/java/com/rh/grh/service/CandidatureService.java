package com.rh.grh.service;

import com.rh.grh.dto.CandidatureDTO;

import java.util.List;

public interface CandidatureService {

    // Créer une nouvelle candidature
    CandidatureDTO createCandidature(CandidatureDTO candidatureDTO);

    // Mettre à jour une candidature
    CandidatureDTO updateCandidature(Long id, CandidatureDTO candidatureDTO);

    // Supprimer une candidature
    void deleteCandidature(Long id);

    // Récupérer toutes les candidatures
    List<CandidatureDTO> getAllCandidatures();

    // Récupérer une candidature par ID
    CandidatureDTO getCandidatureById(Long id);

    // Récupérer toutes les candidatures d'une offre
    List<CandidatureDTO> getCandidaturesByOfferId(Long offerId);
}
