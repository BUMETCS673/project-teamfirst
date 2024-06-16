package edu.bu.metcs673_notification_service.observerpattern;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationSubject {

    private List<NotificationObserver> observers = new ArrayList<>();

    public void register(NotificationObserver observer) {
        observers.add(observer);
    }

    public void unregister(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String messageId, String status, String emailContent) {
        for (NotificationObserver observer : observers) {
            observer.update(messageId, status, emailContent);
        }
    }
}