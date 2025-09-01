package com.rh.grh.mapper;

import com.rh.grh.dto.LeaveDTO;
import com.rh.grh.entity.Leave;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    LeaveDTO toDTO(Leave leave);

    @Mapping(source = "employeeId", target = "employee.id")
    Leave toEntity(LeaveDTO dto);
}
