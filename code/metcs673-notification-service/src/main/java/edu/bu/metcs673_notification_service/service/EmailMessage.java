package edu.bu.metcs673_notification_service.service;

import java.util.UUID;

public class EmailMessage {
    private String messageId;
    private String to;
    private String subject;
    private String body;
    private String name;

    public EmailMessage(String to, String subject, String body, String name) {
        this.messageId = UUID.randomUUID().toString(); //Generate a unique messageId
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.name = name;
    }

    // Getters and Setters
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "messageId='" + messageId + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

