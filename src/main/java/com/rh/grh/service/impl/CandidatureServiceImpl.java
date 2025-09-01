package com.rh.grh.service.impl;

import com.rh.grh.dto.CandidatureDTO;
import com.rh.grh.entity.Candidature;
import com.rh.grh.entity.Offer;
import com.rh.grh.mapper.CandidatureMapper;
import com.rh.grh.repository.CandidatureRepository;
import com.rh.grh.repository.OfferRepository;
import com.rh.grh.service.CandidatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidatureServiceImpl implements CandidatureService {

    private final CandidatureRepository candidatureRepository;
    private final CandidatureMapper candidatureMapper;
    private final OfferRepository offerRepository;

    @Override
    public CandidatureDTO createCandidature(CandidatureDTO dto) {
        Candidature candidature = candidatureMapper.toEntity(dto);
        Candidature saved = candidatureRepository.save(candidature);
        return candidatureMapper.toDTO(saved);
    }

    @Override
    public CandidatureDTO updateCandidature(Long id, CandidatureDTO dto) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature not found"));

        candidature.setNom(dto.getNom());
        candidature.setPrenom(dto.getPrenom());
        candidature.setEmail(dto.getEmail());
        candidature.setCvUrl(dto.getCvUrl());

        if (dto.getOfferId() != null) {
            Offer offer = offerRepository.findById(dto.getOfferId())
                    .orElseThrow(() -> new RuntimeException("Offer not found"));
            candidature.setOffer(offer);
        }

        Candidature updated = candidatureRepository.save(candidature);
        return candidatureMapper.toDTO(updated);
    }


    @Override
    public CandidatureDTO getCandidatureById(Long id) {
        return candidatureRepository.findById(id)
                .map(candidatureMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Candidature not found"));
    }

    @Override
    public List<CandidatureDTO> getAllCandidatures() {
        return candidatureRepository.findAll()
                .stream()
                .map(candidatureMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidatureDTO> getCandidaturesByOfferId(Long offerId) {
        return candidatureRepository.findByOfferId(offerId)
                .stream()
                .map(candidatureMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }
}
