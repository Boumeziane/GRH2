package com.rh.grh.service.impl;

import com.rh.grh.dto.NotificationDTO;
import com.rh.grh.entity.Employee;
import com.rh.grh.entity.Notification;
import com.rh.grh.mapper.NotificationMapper;
import com.rh.grh.repository.EmployeeRepository;
import com.rh.grh.repository.NotificationRepository;
import com.rh.grh.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmployeeRepository employeeRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public NotificationDTO createNotification(NotificationDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Notification notification = notificationMapper.toEntity(dto);
        notification.setEmployee(employee);

        Notification saved = notificationRepository.save(notification);
        return notificationMapper.toDTO(saved);
    }

    @Override
    public NotificationDTO updateNotification(Long id, NotificationDTO dto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setMessage(dto.getMessage());
        notification.setDateEnvoi(dto.getDateEnvoi());
        notification.setLue(dto.getLue());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            notification.setEmployee(employee);
        }

        Notification updated = notificationRepository.save(notification);
        return notificationMapper.toDTO(updated);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public NotificationDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        return notificationMapper.toDTO(notification);
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationsByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return notificationRepository.findByEmployee(employee)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getUnreadNotificationsByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return notificationRepository.findByEmployeeAndLueFalse(employee)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getReadNotificationsByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return notificationRepository.findByEmployeeAndLueTrue(employee)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationsByEmployeeOrderedByDate(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return notificationRepository.findByEmployeeOrderByDateEnvoiDesc(employee)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
