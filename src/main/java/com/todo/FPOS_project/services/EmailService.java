package com.todo.FPOS_project.services;

import org.springframework.scheduling.annotation.Async;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class EmailService {


    @Async
    public void sendEmail(String to, String subject, String htmlContent) throws UnsupportedEncodingException, MessagingException {
        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";
        String username = System.getenv("SUS_EMAIL_USERNAME");
        String password = System.getenv("SUS_EMAIL_PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, "Notifications System"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        htmlContent = htmlContent.replace("{{recipientName}}", "test")
                .replace("{{resetLink}}", "google.com");
        message.setContent(htmlContent, "text/html; charset=utf-8");
        Transport.send(message);
    }

    public String loadEmailTemplate(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }


}