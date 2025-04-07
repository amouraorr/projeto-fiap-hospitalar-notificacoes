package com.fiap.hospitalar.notificacoes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);


    @Autowired
    private JavaMailSender emailSender;

    @KafkaListener(topics = "consultas-agendadas", groupId = "notification-group")
    public void listen(String message) {

        // Log da mensagem recebida
        logger.info("Mensagem recebida do tópico 'consultas-agendadas': {}", message);

        System.out.println("Received message: " + message);


        // Processar a mensagem e enviar notificação
        sendEmail("patient@example.com", "Consulta Agendada", "Detalhes da consulta: " + message);

        // Log após enviar a notificação
        logger.info("Notificação enviada com sucesso para a mensagem: {}", message);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}