package com.rh.grh.mapper;

import com.rh.grh.dto.EvaluationDTO;
import com.rh.grh.entity.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {

    EvaluationMapper INSTANCE = Mappers.getMapper(EvaluationMapper.class);

    @Mapping(source = "employee.id", target = "employeeId")
    EvaluationDTO toDTO(Evaluation evaluation);

    @Mapping(source = "employeeId", target = "employee.id")
    Evaluation toEntity(EvaluationDTO dto);
}
