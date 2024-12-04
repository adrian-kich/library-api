package com.adriankich.library.domain.service;

import com.adriankich.library.domain.dto.PaymentDTO;
import com.adriankich.library.domain.mapper.PaymentMapper;
import com.adriankich.library.domain.model.Payment;
import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.service.utilities.PaymentUtilities;
import com.adriankich.library.infrastructure.repository.PaymentRepository;
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

    public void pay(Rental rental) {
        Payment payment = paymentRepository.save(
                paymentUtilities.createPayment(rental));

        PaymentDTO paymentDTO = PaymentMapper.entityToDto(payment);

        kafkaTemplate.send("lib-payment-topic", paymentDTO);
    }
}
