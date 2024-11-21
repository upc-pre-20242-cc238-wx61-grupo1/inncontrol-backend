package com.github.inncontrol.notification.domain.model.aggregates;

import com.github.inncontrol.notification.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Notification {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String body;
    @Getter
    private String receiver;

    public Notification() {
    }
    public Notification(CreateNotificationCommand command) {
        this.body = command.body();
        this.receiver = command.receiver();
    }
}
