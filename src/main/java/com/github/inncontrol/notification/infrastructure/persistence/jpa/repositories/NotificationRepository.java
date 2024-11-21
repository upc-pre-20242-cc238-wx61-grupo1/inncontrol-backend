package com.github.inncontrol.notification.infrastructure.persistence.jpa.repositories;


import com.github.inncontrol.notification.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
