package com.rh.grh.mapper;

import com.rh.grh.dto.AbsenceDTO;
import com.rh.grh.entity.Absence;
import com.rh.grh.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    AbsenceDTO toDTO(Absence absence);

    @Mapping(source = "employeeId", target = "employee.id")
    Absence toEntity(AbsenceDTO dto);

    // Méthode helper pour créer un Employee avec seulement l'ID
    default Employee createEmployeeFromId(Long employeeId) {
        if (employeeId == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(employeeId);
        return employee;
    }
}
