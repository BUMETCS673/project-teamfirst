package com.example.notification_service.publisher;

import com.example.notification_service.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisher {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendNotification(String message) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "", message);
    }
}
