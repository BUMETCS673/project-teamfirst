package edu.bu.metcs673_notification_service.observerpattern;

import edu.bu.metcs673_notification_service.entity.NotificationEntity;
import edu.bu.metcs673_notification_service.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.TemplateEngine;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NotificationStatusServiceTest {
    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationSubject notificationSubject;

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private NotificationStatusService notificationStatusService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdate() {
        // Arrange
        String messageId = "12345";
        String status = "Sent";
        String emailContent = "Email content";
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessageId(messageId);
        when(notificationRepository.findById(messageId)).thenReturn(Optional.of(notificationEntity));

        // Act
        notificationStatusService.update(messageId, status, emailContent);

        // Assert
        assertEquals(status, notificationEntity.getStatus());
        assertEquals(emailContent, notificationEntity.getEmailContent());
        verify(notificationRepository, times(1)).save(notificationEntity);
        verify(notificationSubject, times(1)).notifyObservers(messageId, status, emailContent);
    }

    @Test
    public void testGetStatusById() {
        // Arrange
        String messageId = "12345";
        String status = "Sent";
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setStatus(status);
        when(notificationRepository.findById(messageId)).thenReturn(Optional.of(notificationEntity));

        // Act
        String result = notificationStatusService.getStatusById(messageId);

        // Assert
        assertEquals(status, result);
    }

    @Test
    public void testGetStatusById_NotFound() {
        // Arrange
        String messageId = "12345";
        when(notificationRepository.findById(messageId)).thenReturn(Optional.empty());

        // Act
        String result = notificationStatusService.getStatusById(messageId);

        // Assert
        assertEquals("unknown", result);
    }

}