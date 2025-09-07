package com.rh.grh.mapper;

import com.rh.grh.dto.EmployeeDTO;
import com.rh.grh.entity.Employee;
import org.mapstruct.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class})
public interface EmployeeMapper {

    // Entity vers DTO - Mapping complet avec relations
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(target = "leaveIds", expression = "java(employee.getLeaves() != null ? employee.getLeaves().stream().map(l -> l.getId()).collect(Collectors.toList()) : null)")
    @Mapping(target = "absenceIds", expression = "java(employee.getAbsences() != null ? employee.getAbsences().stream().map(a -> a.getId()).collect(Collectors.toList()) : null)")
    EmployeeDTO toDTO(Employee employee);

    // DTO vers Entity pour CRÉATION - avec gestion d'Account
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(target = "leaves", ignore = true)
    @Mapping(target = "absences", ignore = true)
    Employee toEntity(EmployeeDTO dto);

    // Méthode pour MISE À JOUR - SEULEMENT les champs de base
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "leaves", ignore = true)
    @Mapping(target = "absences", ignore = true)
    void updateEntityFromDTO(EmployeeDTO dto, @MappingTarget Employee entity);

    // Méthode alternative manuelle pour mise à jour (plus sûre)
    default void updateEmployeeFields(EmployeeDTO dto, Employee entity) {
        if (dto.getNom() != null) {
            entity.setNom(dto.getNom());
        }
        if (dto.getPrenom() != null) {
            entity.setPrenom(dto.getPrenom());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getPoste() != null) {
            entity.setPoste(dto.getPoste());
        }
        if (dto.getDepartment() != null) {
            entity.setDepartment(dto.getDepartment());
        }
        if (dto.getDateEmbauche() != null) {
            entity.setDateEmbauche(dto.getDateEmbauche());
        }
        // Relations Account, Leaves, Absences JAMAIS touchées
    }
}