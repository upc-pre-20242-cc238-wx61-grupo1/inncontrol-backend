package com.github.inncontrol.notification.domain.services;


import com.github.inncontrol.notification.domain.model.aggregates.Notification;
import com.github.inncontrol.notification.domain.model.commands.CreateNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService {

    Optional<Notification> handle(CreateNotificationCommand command);
}
