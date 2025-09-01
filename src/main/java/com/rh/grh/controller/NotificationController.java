package com.rh.grh.controller;

import com.rh.grh.dto.NotificationDTO;
import com.rh.grh.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // ✅ Créer une notification
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(notificationService.createNotification(dto));
    }

    // ✅ Mettre à jour une notification
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(
            @PathVariable Long id,
            @RequestBody NotificationDTO dto
    ) {
        return ResponseEntity.ok(notificationService.updateNotification(id, dto));
    }

    // ✅ Supprimer une notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Obtenir une notification par ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    // ✅ Obtenir toutes les notifications
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    // ✅ Obtenir les notifications d’un employé
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getNotificationsByEmployee(employeeId));
    }

    // ✅ Obtenir les notifications non lues d’un employé
    @GetMapping("/employee/{employeeId}/unread")
    public ResponseEntity<List<NotificationDTO>> getUnreadNotificationsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getUnreadNotificationsByEmployee(employeeId));
    }

    // ✅ Obtenir les notifications lues d’un employé
    @GetMapping("/employee/{employeeId}/read")
    public ResponseEntity<List<NotificationDTO>> getReadNotificationsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getReadNotificationsByEmployee(employeeId));
    }

    // ✅ Obtenir les notifications d’un employé triées par date
    @GetMapping("/employee/{employeeId}/ordered")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByEmployeeOrderedByDate(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getNotificationsByEmployeeOrderedByDate(employeeId));
    }
}
