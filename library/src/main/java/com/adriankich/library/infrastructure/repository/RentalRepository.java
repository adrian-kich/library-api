package com.adriankich.library.infrastructure.repository;

import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.model.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByRenterAndReturned(Renter renter,Boolean returned);
}
