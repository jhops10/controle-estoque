package com.jhops10.controle_estoque.domain.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("notification@w5devcompany.com.br");

        try {
            javaMailSender.send(message);
            System.out.println("Email enviado para: " + to);
        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
        }

    }
}
