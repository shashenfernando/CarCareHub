package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.SendMailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(method = RequestMethod.POST , value ="/sendMail")
    public String sendEmail(@RequestBody SendMailRequest sendMailRequest) throws Exception{

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(sendMailRequest.getSubject());
            mailMessage.setTo(sendMailRequest.getTo());
            mailMessage.setText(sendMailRequest.getMessage());
            mailMessage.setFrom(sendMailRequest.getFrom());

            mailSender.send(mailMessage);
            return "Mail has sent.";
        }
        catch (Exception e){
          return e.getMessage();
        }
    }

}
