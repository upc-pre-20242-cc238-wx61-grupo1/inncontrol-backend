package com.github.inncontrol.notification.domain.services;


import com.github.inncontrol.notification.domain.model.aggregates.Notification;
import com.github.inncontrol.notification.domain.queries.GetAllNotificationQuery;

import java.util.List;

public interface NotificationQueryService {

    List<Notification> handle(GetAllNotificationQuery query);
}
