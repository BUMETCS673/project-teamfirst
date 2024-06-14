package edu.bu.metcs673_notification_service.controller;

import edu.bu.metcs673_notification_service.observerpattern.NotificationSubject;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class NotificationControllerTest {
//mock for rabbittemplate and notificationSubject
    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private NotificationSubject subject;

    @InjectMocks
    //Inject  into notificationController
    private NotificationController notificationController;
    // Initialize the mocks before each test

    @BeforeEach
        // Test method for sendMessage() in NotificationController
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMessage() {
        // sample email obj created
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo("test@example.com");
        emailMessage.setSubject("Test Subject");
        emailMessage.setMessage("Test Message");

        // call send message method of notificationcontroller and record result
        String result = notificationController.sendMessage(emailMessage);

        // verify rabbit convertAndSend called
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), eq(emailMessage));
        assertEquals("Message sent to RabbitMQ", result);
    }
}