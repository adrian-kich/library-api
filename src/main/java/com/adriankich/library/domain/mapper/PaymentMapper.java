package com.adriankich.library.domain.mapper;

import com.adriankich.library.domain.dto.PaymentDTO;
import com.adriankich.library.domain.model.Payment;

public class PaymentMapper {

    public static PaymentDTO entityToDto(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getRental().getId(),
                payment.getValue(),
                payment.getState(),
                payment.getTime()
        );
    }
}
