package com.adriankich.payment.domain.dto;

import com.adriankich.payment.domain.enums.PaymentState;

import java.time.LocalDateTime;

public record PaymentDTO(
        Long id,
        Long rentalId,
        String renterName,
        String renterMail,
        Double value,
        PaymentState state,
        LocalDateTime time
) {}
