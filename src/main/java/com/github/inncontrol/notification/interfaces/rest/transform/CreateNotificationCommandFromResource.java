package com.github.inncontrol.notification.interfaces.rest.transform;


import com.github.inncontrol.notification.domain.model.commands.CreateNotificationCommand;
import com.github.inncontrol.notification.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResource {
    public static CreateNotificationCommand createCommandfromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(resource.body(), resource.receiver());
    }
}
