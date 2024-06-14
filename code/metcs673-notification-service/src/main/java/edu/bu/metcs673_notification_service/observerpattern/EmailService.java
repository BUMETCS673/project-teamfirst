package edu.bu.metcs673_notification_service.observerpattern;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.mail.javamail.MimeMessagePreparator;

@Service
public class EmailService extends NotificationObserver {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void update(String message) {
        //JSON message. Parse it to EmailMessage object.
        EmailMessage emailMessage = parseMessage(message);
        sendHtmlMessage(emailMessage);
    }

    private EmailMessage parseMessage(String message) {
        return new EmailMessage("team1metcs673@gmail.com", "Welcome", message, "CS673");
    }

    public void sendHtmlMessage(EmailMessage emailMessage) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(emailMessage.getTo());
            helper.setSubject(emailMessage.getSubject());

            Context context = new Context();
            context.setVariable("name", emailMessage.getName());
            context.setVariable("message", emailMessage.getBody());

            String htmlBody = templateEngine.process("emailTemplate", context);
            helper.setText(htmlBody, true);
        };

        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}