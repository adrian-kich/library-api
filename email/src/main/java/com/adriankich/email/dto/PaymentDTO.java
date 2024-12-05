package com.adriankich.email.dto;

import com.adriankich.email.enums.PaymentState;

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
