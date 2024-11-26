package com.adriankich.library.infrastructure.repository;

import com.adriankich.library.domain.model.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RenterRepository extends JpaRepository<Renter, Long> {
    boolean existsByCpf(String cpf);
}
