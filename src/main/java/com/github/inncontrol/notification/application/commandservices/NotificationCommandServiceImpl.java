package com.github.inncontrol.notification.application.commandservices;

import com.twilio.rest.api.v2010.account.Message;

import com.github.inncontrol.notification.domain.model.aggregates.Notification;
import com.github.inncontrol.notification.domain.model.commands.CreateNotificationCommand;
import com.github.inncontrol.notification.domain.services.NotificationCommandService;
import com.github.inncontrol.notification.infrastructure.persistence.jpa.repositories.NotificationRepository;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String phoneNumber;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        // Crear la notificación
        var notification = new Notification(command);
        notificationRepository.save(notification);

        // Enviar notificación con Twilio
        try {
            // Inicializar Twilio
            Twilio.init(accountSid, authToken);

            // Enviar mensaje SMS
            Message message = Message.creator(
                    new PhoneNumber(command.receiver()),  // Número de teléfono receptor
                    new PhoneNumber(phoneNumber),         // Número de teléfono Twilio
                    command.body()                        // Cuerpo del mensaje
            ).create();

            // Verificación o manejo del mensaje
            System.out.println("Mensaje enviado con SID: " + message.getSid());

        } catch (Exception e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage());
        }

        return Optional.of(notification);
    }
}
