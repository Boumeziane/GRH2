package com.rh.grh.service;

import com.rh.grh.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    NotificationDTO createNotification(NotificationDTO dto);

    NotificationDTO updateNotification(Long id, NotificationDTO dto);

    void deleteNotification(Long id);

    NotificationDTO getNotificationById(Long id);

    List<NotificationDTO> getAllNotifications();

    List<NotificationDTO> getNotificationsByEmployee(Long employeeId);

    List<NotificationDTO> getUnreadNotificationsByEmployee(Long employeeId);

    List<NotificationDTO> getReadNotificationsByEmployee(Long employeeId);

    List<NotificationDTO> getNotificationsByEmployeeOrderedByDate(Long employeeId);
}
