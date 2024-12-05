package com.adriankich.library.domain.mapper;

import com.adriankich.library.domain.dto.PaymentDTO;
import com.adriankich.library.domain.model.Payment;
import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.model.Renter;

public class PaymentMapper {

    public static PaymentDTO entityToDto(Payment payment) {
        Rental rental = payment.getRental();
        Renter renter = rental.getRenter();

        return new PaymentDTO(
                payment.getId(),
                rental.getId(),
                renter.getName(),
                renter.getEmail(),
                payment.getValue(),
                payment.getState(),
                payment.getTime()
        );
    }
}
