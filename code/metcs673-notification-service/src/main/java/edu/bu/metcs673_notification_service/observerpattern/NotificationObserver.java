package edu.bu.metcs673_notification_service.observerpattern;

public interface NotificationObserver {
    void update(String messageId, String status, String emailContent);
}