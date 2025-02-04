package com.example.carcarehub.service;

import com.example.carcarehub.model.response.SendEmailResponse;

public interface EmailSenderService {
    public SendEmailResponse sendEmail(String toEmail, String FromEmail, String subject , String body)throws Exception;
}
