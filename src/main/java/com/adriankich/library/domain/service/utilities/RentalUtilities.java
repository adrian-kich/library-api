package com.adriankich.library.domain.service.utilities;

import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.model.Renter;
import com.adriankich.library.infrastructure.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class RentalUtilities {

    @Autowired
    private RentalRepository rentalRepository;

    public Rental getRentalById(Long id) {
        return rentalRepository
                .findById(id)
                .orElseThrow(
                    () -> new NotFoundException(
                    String.format("Não foi encontrado um aluguel com o id: #%s", id)));
    }

    public List<Rental> getRentalByRenter(Renter renter, boolean returned) {
        return rentalRepository.findByRenterAndReturned(renter, returned);
    }

    public Double calculateAmountToPay(Rental rental) {
        final double DAILY_VALUE = 2.0;
        final double FINE_VALUE = 1.0;
        final LocalTime LIMIT_RETURN = LocalTime.of(22, 0);

        LocalDate rentalDate = rental.getRentalDate();
        LocalDate returnDate = rental.getReturnDate();
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        long rentalDays = ChronoUnit.DAYS.between(rentalDate, returnDate) + 1;
        double value = rentalDays * DAILY_VALUE;

        if (today.isAfter(returnDate) || (today.isEqual(returnDate) && now.isAfter(LIMIT_RETURN))) {
            long delay = ChronoUnit.DAYS.between(returnDate, today);
            if (now.isAfter(LIMIT_RETURN)) {
                delay += 1;
            }
            value += delay * FINE_VALUE;
        }

        return value;
    }
}
