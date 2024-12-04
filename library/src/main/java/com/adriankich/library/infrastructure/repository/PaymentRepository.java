package com.adriankich.library.infrastructure.repository;

import com.adriankich.library.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
