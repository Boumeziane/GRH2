package com.rh.grh.mapper;

import com.rh.grh.dto.CandidatureDTO;
import com.rh.grh.entity.Candidature;
import com.rh.grh.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidatureMapper {

    @Mapping(source = "offer.id", target = "offerId")
    CandidatureDTO toDTO(Candidature candidature);

    @Mapping(target = "offer", expression = "java(createOfferFromId(dto.getOfferId()))")
    Candidature toEntity(CandidatureDTO dto);

    // Méthode helper pour créer un Offer avec seulement l'ID
    default Offer createOfferFromId(Long offerId) {
        if (offerId == null) {
            return null;
        }
        Offer offer = new Offer();
        offer.setId(offerId);
        return offer;
    }
}