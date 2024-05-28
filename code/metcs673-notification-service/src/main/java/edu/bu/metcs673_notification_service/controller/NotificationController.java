package edu.bu.metcs673_notification_service.controller;

import edu.bu.metcs673_notification_service.observerpattern.NotificationSubject;
import edu.bu.metcs673_notification_service.publisher.NotificationPublisher;
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