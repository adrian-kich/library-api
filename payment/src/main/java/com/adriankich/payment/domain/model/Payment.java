package com.adriankich.payment.domain.model;

import com.adriankich.payment.domain.enums.PaymentState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "lib_payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PaymentState state;

    @Column(nullable = false)
    private LocalDateTime time;

    public void setState(PaymentState state) {
        this.state = state;
    }
}
