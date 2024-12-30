package com.adriankich.library.stub;

import com.adriankich.library.domain.model.Rental;

import java.time.LocalDate;
import java.util.List;

public interface RentalStub {

    static Rental createRentalStub() {
        return Rental.builder()
                .id(1L)
                .rentalDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(3))
                .returned(false)
                .books(List.of(BookStub.createBookStub()))
                .renter(RenterStub.createRenterStub())
                .build();
    }
}
