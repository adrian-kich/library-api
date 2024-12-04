package com.adriankich.library.domain.service.utilities;

import com.adriankich.library.domain.enums.PaymentState;
import com.adriankich.library.domain.model.Payment;
import com.adriankich.library.domain.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentUtilities {

    @Autowired
    private RentalUtilities rentalUtilities;

    public Payment createPayment(Rental rental) {
        return Payment.builder()
                .rental(rental)
                .value(rentalUtilities.calculateAmountToPay(rental))
                .state(PaymentState.IN_PROCESSING)
                .time(LocalDateTime.now())
                .build();
    }
}
