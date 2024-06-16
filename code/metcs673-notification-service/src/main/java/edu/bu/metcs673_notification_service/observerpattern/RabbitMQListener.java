package edu.bu.metcs673_notification_service.observerpattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.metcs673_notification_service.config.RabbitMQConfig;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import edu.bu.metcs673_notification_service.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.HashMap;
import java.util.Map;


@Service
public class RabbitMQListener {

    @Autowired
    private NotificationSubject notificationSubject;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        try {
            EmailMessage emailMessage = objectMapper.readValue(message, EmailMessage.class);

            // Prepare the template model
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("name", emailMessage.getName());
            templateModel.put("message", emailMessage.getBody());

            // Send the HTML email
            String emailContent = emailService.sendHtmlMessage(emailMessage.getTo(), emailMessage.getSubject(), "emailTemplate", templateModel);

            // Update status and notify observers
            notificationSubject.notifyObservers(emailMessage.getMessageId(), "delivered", emailContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}