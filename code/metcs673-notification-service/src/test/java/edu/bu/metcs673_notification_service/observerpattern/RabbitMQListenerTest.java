package edu.bu.metcs673_notification_service.observerpattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import edu.bu.metcs673_notification_service.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RabbitMQListenerTest {

    @Mock
    private NotificationSubject notificationSubject;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private RabbitMQListener rabbitMQListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceiveMessage_successful() throws Exception {
        String message = "{\"to\":\"test@example.com\",\"subject\":\"Test Subject\",\"name\":\"Test Name\",\"body\":\"Test Body\",\"messageId\":\"12345\"}";

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo("test@example.com");
        emailMessage.setSubject("Test Subject");
        emailMessage.setName("Test Name");
        emailMessage.setBody("Test Body");
        emailMessage.setMessageId("12345");

        when(objectMapper.readValue(message, EmailMessage.class)).thenReturn(emailMessage);
        when(emailService.sendHtmlMessage(eq(emailMessage.getTo()), eq(emailMessage.getSubject()), eq("emailTemplate"), anyMap())).thenReturn("Email Content");

        rabbitMQListener.receiveMessage(message);

        verify(notificationSubject).notifyObservers("12345", "delivered", "Email Content");
    }

    @Test
    void testReceiveMessage_exception() throws Exception {
        String message = "{\"to\":\"test@example.com\",\"subject\":\"Test Subject\",\"name\":\"Test Name\",\"body\":\"Test Body\",\"messageId\":\"12345\"}";

        when(objectMapper.readValue(message, EmailMessage.class)).thenThrow(new RuntimeException("JSON parsing error"));

        rabbitMQListener.receiveMessage(message);

        verify(notificationSubject, never()).notifyObservers(anyString(), anyString(), anyString());
        verify(emailService, never()).sendHtmlMessage(anyString(), anyString(), anyString(), anyMap());
    }
}