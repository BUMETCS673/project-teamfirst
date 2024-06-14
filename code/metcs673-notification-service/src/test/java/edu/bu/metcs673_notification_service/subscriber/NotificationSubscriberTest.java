package edu.bu.metcs673_notification_service.subscriber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationSubscriberTest {

    private NotificationSubscriber notificationSubscriber;

    @BeforeEach
    //set up sample test message
    void setUp() {
        notificationSubscriber = new NotificationSubscriber();
    }

    @Test
    // make sure calling receviveEmailNotification called , do not throw any exceptions
    void receiveEmailNotification() {
        String message = "Test Email Notification";
        assertDoesNotThrow(() -> notificationSubscriber.receiveEmailNotification(message));
    }
}
