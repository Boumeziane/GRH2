package com.rh.grh.mapper;

import com.rh.grh.dto.OfferDTO;
import com.rh.grh.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CandidatureMapper.class})
public interface OfferMapper {

    // Conversion Offer -> OfferDTO
    OfferDTO toDTO(Offer offer);

    // Conversion OfferDTO -> Offer
    Offer toEntity(OfferDTO dto);
}
