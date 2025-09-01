package com.rh.grh.repository;

import com.rh.grh.entity.Notification;
import com.rh.grh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Récupérer toutes les notifications d'un employé
    List<Notification> findByEmployee(Employee employee);

    // Récupérer les notifications non lues
    List<Notification> findByEmployeeAndLueFalse(Employee employee);

    // Récupérer les notifications lues
    List<Notification> findByEmployeeAndLueTrue(Employee employee);

    // Récupérer les notifications par ordre de date décroissante
    List<Notification> findByEmployeeOrderByDateEnvoiDesc(Employee employee);
}
