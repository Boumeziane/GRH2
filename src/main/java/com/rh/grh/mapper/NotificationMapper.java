package com.rh.grh.mapper;

import com.rh.grh.dto.NotificationDTO;
import com.rh.grh.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "employee.id", target = "employeeId")
    NotificationDTO toDTO(Notification notification);

    @Mapping(source = "employeeId", target = "employee.id")
    Notification toEntity(NotificationDTO dto);
}
