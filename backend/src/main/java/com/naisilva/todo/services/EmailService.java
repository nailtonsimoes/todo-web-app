package com.naisilva.todo.services;

import com.naisilva.todo.config.enums.StatusEmail;
import com.naisilva.todo.domain.EmailEntity;
import com.naisilva.todo.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;


    public EmailEntity sendEmail(EmailEntity emailEntity) {
        emailEntity.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailEntity.getEmailFrom());
            message.setTo(emailEntity.getEmailTo());
            message.setSubject(emailEntity.getSubject());
            message.setText(emailEntity.getText());
            emailSender.send(message);

            emailEntity.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailEntity.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailEntity);
        }
    }
}
