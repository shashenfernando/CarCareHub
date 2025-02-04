package com.example.carcarehub.service;

import com.example.carcarehub.model.response.SendEmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public SendEmailResponse sendEmail(String toEmail, String fromEmail, String subject, String body) throws Exception {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setFrom(fromEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);

        SendEmailResponse response = new SendEmailResponse();
        response.setToEmail(mailMessage.getTo());
        response.setFromEmail(response.getFromEmail());
        response.setSubject(mailMessage.getSubject());
        response.setBody(mailMessage.getText());

        return response;
    }
}
