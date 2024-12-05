package com.adriankich.library.domain.dto;

import com.adriankich.library.domain.enums.PaymentState;

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
