package com.rh.grh.service.impl;

import com.rh.grh.dto.OfferDTO;
import com.rh.grh.entity.Offer;
import com.rh.grh.mapper.OfferMapper;
import com.rh.grh.repository.OfferRepository;
import com.rh.grh.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Override
    public OfferDTO createOffer(OfferDTO dto) {
        Offer offer = offerMapper.toEntity(dto);
        Offer saved = offerRepository.save(offer);
        return offerMapper.toDTO(saved);
    }

    @Override
    public OfferDTO updateOffer(Long id, OfferDTO dto) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setTitre(dto.getTitre());
        offer.setDescription(dto.getDescription());
        offer.setDatePublication(dto.getDatePublication());
        // ⚠️ Les candidatures sont gérées automatiquement via JPA si besoin

        Offer updated = offerRepository.save(offer);
        return offerMapper.toDTO(updated);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public OfferDTO getOfferById(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        return offerMapper.toDTO(offer);
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(offerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersByTitle(String titre) {
        return offerRepository.findByTitreContainingIgnoreCase(titre)
                .stream()
                .map(offerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersByDate(LocalDate datePublication) {
        return offerRepository.findByDatePublication(datePublication)
                .stream()
                .map(offerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersByDateRange(LocalDate startDate, LocalDate endDate) {
        return offerRepository.findByDatePublicationBetween(startDate, endDate)
                .stream()
                .map(offerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
