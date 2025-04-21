package com.jhops10.controle_estoque.domain.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Captor
    ArgumentCaptor<SimpleMailMessage> messageCaptor;

    @Test
    void shouldSendEmailSuccessfully() {
        String to = "destinatario@email.com";
        String subject = "Assunto de teste";
        String body = "conteúdo do email teste";

        emailService.sendEmail(to, subject, body);

        verify(javaMailSender).send(messageCaptor.capture());

        SimpleMailMessage capturedMessage = messageCaptor.getValue();

        assertEquals(to, capturedMessage.getTo()[0]);
        assertEquals(subject, capturedMessage.getSubject());
        assertEquals(body, capturedMessage.getText());
        assertEquals("notification@w5devcompany.com.br", capturedMessage.getFrom());
    }

    @Test
    void shouldThrowExceptionWhenSendingEmailFails() {
        String to = "erro@email.com";
        String subject = "Erro ao enviar.";
        String body = "Este email não deve ser enviado";

        doThrow(new MailSendException("Falha ao enviar"))
                .when(javaMailSender).send(any(SimpleMailMessage.class));

        assertDoesNotThrow(() -> emailService.sendEmail(to, subject, body));

        verify(javaMailSender).send(any(SimpleMailMessage.class));
    }
}