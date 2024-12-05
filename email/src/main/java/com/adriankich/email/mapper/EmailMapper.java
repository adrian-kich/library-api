package com.adriankich.email.mapper;

import com.adriankich.email.dto.PaymentDTO;
import com.adriankich.email.model.Email;

public class EmailMapper {

    public static Email dtoToEntity(PaymentDTO paymentDTO) {
        return Email.builder()
                .receiver(paymentDTO.renterMail())
                .paymentId(paymentDTO.id())
                .build();
    }
}
