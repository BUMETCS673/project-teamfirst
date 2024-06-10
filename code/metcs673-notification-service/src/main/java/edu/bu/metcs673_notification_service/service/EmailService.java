package edu.bu.metcs673_notification_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendHtmlMessage(String to, String subject, String message, String name) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);

            // Generate the HTML content using Thymeleaf
            Context context = new Context();
            context.setVariable("message", message);
            context.setVariable("name", name);
            String htmlBody = templateEngine.process("emailTemplate", context);

            helper.setText(htmlBody, true);
            emailSender.send(mimeMessage);
            logger.info("Email sent successfully to {}",to);
        } catch (MessagingException e) {
            logger.error("Failed sent email", e);
        }
    }
}