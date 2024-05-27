package com.example.notification_service.subscriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationSubscriber {

    @RabbitListener(queues = "email_queue")
    public void receiveEmailNotification(String message) {
        System.out.println("Received Email Notification: " + message);
    }
}
