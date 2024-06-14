package edu.bu.metcs673_notification_service.observerpattern;

import edu.bu.metcs673_notification_service.config.RabbitMQConfig;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;



@Service
public class RabbitMQListener {

    private final NotificationSubject messageSubject;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @Autowired
    public RabbitMQListener(NotificationSubject messageSubject) {
        this.messageSubject = messageSubject;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(String message) {
        logger.info("Email received" + message);
        messageSubject.notifyObservers(message);
    }
}