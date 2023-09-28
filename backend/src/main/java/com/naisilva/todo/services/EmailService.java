package com.naisilva.todo.services;

import com.naisilva.todo.config.enums.StatusEmail;
import com.naisilva.todo.domain.EmailEntity;
import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.EmailRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private final EmailRepository emailRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TokenService tokenService;

    public EmailService(UserRepository userRepository, EmailRepository emailRepository, TokenService tokenService) {
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Autowired
    private JavaMailSender emailSender;


    public EmailEntity forgotPassword(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "Usuario não encontrado!"
                        )
                );

        String url = "http://localhost:4200/recover-password?token=" + tokenService.tokenGenerate(user);

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setOwnerRef("ADM - Todo Web App");
        emailEntity.setSubject("ToDo Web App - Link para recuperação de senha");
        emailEntity.setEmailFrom("nailtonsimoes@live.com");
        emailEntity.setEmailTo(user.getEmail());
        emailEntity.setText("Aqui esta o link para recuperar sua senha : " + url + "\n\n To Do App.");
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
        }

        return emailRepository.save(emailEntity);
    }
}
