package edu.bu.metcs673_notification_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.metcs673_notification_service.config.RabbitMQConfig;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import edu.bu.metcs673_notification_service.observerpattern.NotificationStatusService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Observed
@RequestMapping("/v1/notifications")
@CrossOrigin(origins = "*")
@Tag(name = "Notification Management", description = "APIs for managing notifications")
public class NotificationController {

    private RabbitTemplate customRabbitTemplate;
    private ObjectMapper objectMapper;
    private NotificationStatusService notificationStatusService;

    @Autowired
    public NotificationController(RabbitTemplate customRabbitTemplate, ObjectMapper objectMapper, NotificationStatusService notificationStatusService) {
        this.customRabbitTemplate = customRabbitTemplate;
        this.objectMapper = objectMapper;
        this.notificationStatusService = notificationStatusService;
    }

    @Operation(summary = "Send an email notification", description = "Endpoint to send an email notification.")
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailMessage(@RequestBody EmailMessage emailMessage) {
        try {
            String message = objectMapper.writeValueAsString(emailMessage);
            customRabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message);

            // Prepare the template model
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("name", emailMessage.getName());
            templateModel.put("message", emailMessage.getBody());

            // Generate HTML content for the email
            String emailContent = notificationStatusService.generateEmailContent(emailMessage.getTo(), emailMessage.getSubject(), "emailTemplate", templateModel);

            notificationStatusService.saveNotification(emailMessage, "sent", emailContent); // Save notification to database with status "sent"

            return ResponseEntity.ok("Email sent to RabbitMQ");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }

    @Operation(summary = "Get email notification status", description = "Endpoint to retrieve the status of an email notification by its message ID.")
    @GetMapping("/status/{messageId}")
    public ResponseEntity<String> getNotificationStatus(@Parameter(description = "ID of the message to get the status for") @PathVariable String messageId) {
        try {
            String status = notificationStatusService.getStatusById(messageId);
            return ResponseEntity.ok("Notification status: " + status);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to get status: " + e.getMessage());
        }
    }
}

