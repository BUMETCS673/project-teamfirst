package edu.bu.metcs673_notification_service.controller;

import edu.bu.metcs673_notification_service.config.RabbitMQConfig;
import edu.bu.metcs673_notification_service.observerpattern.NotificationSubject;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class NotificationController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private NotificationSubject subject;

    @PostMapping
    public String sendMessage(@RequestBody EmailMessage emailMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", emailMessage);
        return "Message sent to RabbitMQ";
    }
}
