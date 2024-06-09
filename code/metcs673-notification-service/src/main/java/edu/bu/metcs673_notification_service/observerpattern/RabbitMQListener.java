package edu.bu.metcs673_notification_service.observerpattern;

import edu.bu.metcs673_notification_service.config.RabbitMQConfig;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import edu.bu.metcs673_notification_service.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMQListener extends EmailNotificationObserver {

    @Autowired
    public EmailService emailService;

    public RabbitMQListener(NotificationSubject subject) {
        super(subject);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(EmailMessage message) {
        System.out.println("Received message: " + message.getMessage());
        subject.setMessage(message.getMessage());
        emailService.sendHtmlMessage(message.getTo(), message.getSubject(), message.getMessage(), message.getName());
    }

}
