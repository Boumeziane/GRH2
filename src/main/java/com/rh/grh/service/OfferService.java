package com.rh.grh.service;

import com.rh.grh.dto.OfferDTO;

import java.time.LocalDate;
import java.util.List;

public interface OfferService {

    OfferDTO createOffer(OfferDTO dto);

    OfferDTO updateOffer(Long id, OfferDTO dto);

    void deleteOffer(Long id);

    OfferDTO getOfferById(Long id);

    List<OfferDTO> getAllOffers();

    List<OfferDTO> getOffersByTitle(String titre);

    List<OfferDTO> getOffersByDate(LocalDate datePublication);

    List<OfferDTO> getOffersByDateRange(LocalDate startDate, LocalDate endDate);
}
