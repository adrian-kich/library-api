package com.adriankich.email.service;

import com.adriankich.email.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "lib-payment-topic", containerFactory = "emailKafkaListenerContainerFactory")
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "5",
            autoCreateTopics = "true",
            include = Exception.class
    )
    public void listenerPaymentProcessing(PaymentDTO paymentDTO) {
        System.out.println("Mensagem recebida: PaymentId = " + paymentDTO.id());
        emailService.sendMailPaymentProcessing(paymentDTO);
    }

    @KafkaListener(topics = "lib-payment-success-topic", containerFactory = "emailKafkaListenerContainerFactory")
    public void listenerPaymentSuccess(PaymentDTO paymentDTO) {
        System.out.println("Mensagem recebida, pagamento concluído: PaymentId = " + paymentDTO.id());
        emailService.sendMailPaymentProcessed(paymentDTO);
    }
}
