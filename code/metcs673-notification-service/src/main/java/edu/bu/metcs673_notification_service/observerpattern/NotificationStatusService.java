package edu.bu.metcs673_notification_service.observerpattern;

import edu.bu.metcs673_notification_service.entity.NotificationEntity;
import edu.bu.metcs673_notification_service.repository.NotificationRepository;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;
import java.util.Map;
import java.util.Optional;

@Service
public class NotificationStatusService implements NotificationObserver {

    private NotificationRepository notificationRepository;
    private NotificationSubject notificationSubject;
    private TemplateEngine templateEngine;

    @Autowired
    public NotificationStatusService(NotificationRepository notificationRepository, NotificationSubject notificationSubject, TemplateEngine templateEngine) {
        this.notificationRepository = notificationRepository;
        this.notificationSubject = notificationSubject;
        this.templateEngine = templateEngine;
        this.notificationSubject.register(this); // Register as an observer
    }

    public String getStatusById(String messageId) {
        Optional<NotificationEntity> notification = notificationRepository.findById(messageId);
        return notification.map(NotificationEntity::getStatus).orElse("unknown");
    }

    public void updateStatus(String messageId, String status, String emailContent) {
        Optional<NotificationEntity> notification = notificationRepository.findById(messageId);
        notification.ifPresent(n -> {
            n.setStatus(status);
            n.setEmailContent(emailContent);
            notificationRepository.save(n);
            notificationSubject.notifyObservers(messageId, status, emailContent); // Notify observers
        });
    }

    public void saveNotification(EmailMessage emailMessage, String status, String emailContent) {
        NotificationEntity notification = new NotificationEntity();
        notification.setMessageId(emailMessage.getMessageId());
        notification.setTo(emailMessage.getTo());
        notification.setSubject(emailMessage.getSubject());
        notification.setBody(emailMessage.getBody());
        notification.setName(emailMessage.getName());
        notification.setStatus(status);
        notification.setEmailContent(emailContent);
        notificationRepository.save(notification);
        notificationSubject.notifyObservers(emailMessage.getMessageId(), status, emailContent); // Notify observers
    }

    @Override
    public void update(String messageId, String status, String emailContent) {
        // Implement any specific behavior needed when the notification status or content is updated
        System.out.println("Notification " + messageId + " status updated to: " + status);
        System.out.println("Email Content: " + emailContent);
    }

    public String generateEmailContent(String to, String subject, String templateName, Map<String, Object> templateModel) {
        Context context = new Context();
        context.setVariables(templateModel);
        return templateEngine.process(templateName, context);
    }
}
