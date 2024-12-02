package com.adriankich.library.domain.service.utilities;

import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.model.Renter;
import com.adriankich.library.infrastructure.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
