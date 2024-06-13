package edu.bu.metcs673_notification_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.metcs673_notification_service.config.RabbitMQConfig;
import edu.bu.metcs673_notification_service.observerpattern.NotificationSubject;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/send-email")
public class NotificationController {

    @Autowired
    private RabbitTemplate customRabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NotificationSubject subject;

    @PostMapping
    public String sendEmailMessage(@RequestBody EmailMessage emailMessage) {
        try {
            String message = objectMapper.writeValueAsString(emailMessage);
            customRabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message);
            return "Email sent to RabbitMQ";
        } catch (JsonProcessingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
