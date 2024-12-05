package com.adriankich.payment.domain.service.utilities;

import com.adriankich.payment.domain.exception.NotFoundException;
import com.adriankich.payment.domain.model.Payment;
import com.adriankich.payment.infrastructure.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentUtilities {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getPaymentById(Long id) {
        return paymentRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("Não foi encontrado um pagamento com o id: #%s", id)));
    }
}
