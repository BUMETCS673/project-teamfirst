package edu.bu.metcs673_notification_service.observerpattern;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailNotificationObserverTest {

    @Test
    void update() {
        // create a mock subject
        NotificationSubject subject = mock(NotificationSubject.class);
        EmailNotificationObserver observer = new EmailNotificationObserver(subject);
        // call observer method
        observer.update();
        // verify mock subject was called
        verify(subject, times(1)).getMessage();

    }
}