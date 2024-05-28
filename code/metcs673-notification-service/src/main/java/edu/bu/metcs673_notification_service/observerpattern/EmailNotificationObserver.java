package edu.bu.metcs673_notification_service.observerpattern;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationObserver implements NotificationObserver{

    @Override
    public void update(String message) {
        System.out.println("Email Notification Observer: " + message);
    }
}