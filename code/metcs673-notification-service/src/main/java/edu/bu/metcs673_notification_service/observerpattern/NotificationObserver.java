package edu.bu.metcs673_notification_service.observerpattern;

public abstract class NotificationObserver {
    public NotificationSubject subject;
    public abstract void update();
}