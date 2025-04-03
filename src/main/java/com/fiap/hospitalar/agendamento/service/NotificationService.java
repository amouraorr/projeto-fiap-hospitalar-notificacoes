package com.fiap.hospitalar.agendamento.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "appointments", groupId = "notification-group")
    public void listen(String message) {
        // Processar a mensagem recebida e enviar lembrete
        System.out.println("Received Message: " + message);
        // Lógica para enviar lembrete ao paciente
    }
}