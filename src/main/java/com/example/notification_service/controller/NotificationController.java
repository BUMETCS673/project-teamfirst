package com.example.notification_service.controller;

import com.example.notification_service.observerpattern.NotificationSubject;
import com.example.notification_service.publisher.NotificationPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationPublisher notificationPublisher;
    @Autowired
    private NotificationSubject notificationSubject;

    @PostMapping("/send-notification")
    public String sendNotification(@RequestParam String message) {
        notificationPublisher.sendNotification(message);
        notificationSubject.notifyObservers(message);
        return "Notification sent";
    }
}
