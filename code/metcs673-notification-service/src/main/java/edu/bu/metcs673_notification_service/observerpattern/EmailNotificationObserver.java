package edu.bu.metcs673_notification_service.observerpattern;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationObserver extends NotificationObserver{
    public EmailNotificationObserver(NotificationSubject subject) {
        this.subject = subject;
        this.subject.register(this);
    }

    @Override
    public void update() {
        System.out.println("Email Notification Observer: " + subject.getMessage());
    }
}