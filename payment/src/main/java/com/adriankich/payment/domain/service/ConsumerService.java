package com.adriankich.payment.domain.service;

import com.adriankich.payment.domain.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "lib-payment-topic", containerFactory = "paymentKafkaListenerContainerFactory")
    public void listener(PaymentDTO paymentDTO) {
        System.out.println("Mensagem recebida: PaymentId = " + paymentDTO.id());
        paymentService.validatePayment(paymentDTO);
    }
}
