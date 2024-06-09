package edu.bu.metcs673_notification_service.observerpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationSubject {

    private List<NotificationObserver> observers = new ArrayList<>();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }


    public void register(NotificationObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for(NotificationObserver observer : observers) {
            observer.update();
        }
    }
}