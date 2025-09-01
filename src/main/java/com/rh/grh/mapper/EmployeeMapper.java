package com.rh.grh.mapper;

import com.rh.grh.dto.EmployeeDTO;
import com.rh.grh.entity.Employee;


import org.mapstruct.*;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring", imports = {Collectors.class})
public interface EmployeeMapper {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(target = "leaveIds", expression = "java(employee.getLeaves() != null ? employee.getLeaves().stream().map(l -> l.getId()).collect(Collectors.toList()) : null)")
    @Mapping(target = "absenceIds", expression = "java(employee.getAbsences() != null ? employee.getAbsences().stream().map(a -> a.getId()).collect(Collectors.toList()) : null)")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(source = "accountId", target = "account.id")
    @Mapping(target = "leaves", ignore = true)
    @Mapping(target = "absences", ignore = true)
    Employee toEntity(EmployeeDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(target = "leaves", ignore = true)
    @Mapping(target = "absences", ignore = true)
    void updateEntityFromDTO(EmployeeDTO dto, @MappingTarget Employee entity);
}

