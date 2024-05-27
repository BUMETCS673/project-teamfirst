package com.example.notification_service.observerpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationSubject {

    private List<NotificationObserver> observers = new ArrayList<>();

    @Autowired
    public NotificationSubject(List<NotificationObserver> observers) {
        this.observers = observers;
    }

    public void notifyObservers(String message) {
        for(NotificationObserver observer : observers) {
            observer.update(message);
        }
    }
}
