package com.github.inncontrol.notification.application.queryservices;

import com.github.inncontrol.notification.domain.model.aggregates.Notification;
import com.github.inncontrol.notification.domain.queries.GetAllNotificationQuery;
import com.github.inncontrol.notification.domain.services.NotificationQueryService;
import com.github.inncontrol.notification.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationQuery query) {
        return notificationRepository.findAll();
    }
}
