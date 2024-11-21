package com.github.inncontrol.notification.interfaces.rest.transform;


import com.github.inncontrol.notification.domain.model.aggregates.Notification;
import com.github.inncontrol.notification.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResource(Notification notification) {
        return new NotificationResource(notification.getId(), notification.getBody(), notification.getReceiver());
    }
}
