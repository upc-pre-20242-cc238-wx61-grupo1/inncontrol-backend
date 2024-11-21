package com.github.inncontrol.notification.interfaces.rest;

import com.github.inncontrol.notification.domain.queries.GetAllNotificationQuery;
import com.github.inncontrol.notification.domain.services.NotificationCommandService;
import com.github.inncontrol.notification.domain.services.NotificationQueryService;
import com.github.inncontrol.notification.interfaces.rest.resources.CreateNotificationResource;
import com.github.inncontrol.notification.interfaces.rest.resources.NotificationResource;
import com.github.inncontrol.notification.interfaces.rest.transform.CreateNotificationCommandFromResource;
import com.github.inncontrol.notification.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/notification", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notification", description = "Notification Management Endpoints")
public class NotificationController {

    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource resource) {
        var createNotificationCommand = CreateNotificationCommandFromResource.createCommandfromResource(resource);
        var notification = notificationCommandService.handle(createNotificationCommand);
        if (notification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResource(notification.get());
        return ResponseEntity.ok(notificationResource);

    }

    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var notifications = notificationQueryService.handle( new GetAllNotificationQuery());
        var notificationResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResource).toList();
        return ResponseEntity.ok(notificationResources);
    }

}
