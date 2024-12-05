package com.adriankich.payment.domain.service;

import com.adriankich.payment.domain.dto.PaymentDTO;
import com.adriankich.payment.domain.enums.PaymentState;
import com.adriankich.payment.domain.model.Payment;
import com.adriankich.payment.domain.service.utilities.PaymentUtilities;
import com.adriankich.payment.infrastructure.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentUtilities paymentUtilities;

    @Autowired
    private KafkaTemplate<String, PaymentDTO> kafkaTemplate;

    public void validatePayment(PaymentDTO paymentDTO) {
        Payment payment = paymentUtilities.getPaymentById(paymentDTO.id());

        payment.setState(PaymentState.PROCESSED);
        paymentRepository.save(payment);
        System.out.println("Pagamento processado: PaymentId = " + paymentDTO.id());

        kafkaTemplate.send("lib-payment-success-topic", paymentDTO);
    }
}
