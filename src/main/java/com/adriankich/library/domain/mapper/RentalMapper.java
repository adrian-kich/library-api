package com.adriankich.library.domain.mapper;

import com.adriankich.library.application.dto.request.RentalRequestDTO;
import com.adriankich.library.application.dto.response.RentalResponseDTO;
import com.adriankich.library.domain.context.ApplicationContext;
import com.adriankich.library.domain.model.Rental;

import java.time.LocalDate;

public class RentalMapper {
    final static ApplicationContext context = ApplicationContext.getInstance();

    public static Rental dtoToEntity(RentalRequestDTO rentalRequestDTO) {
        LocalDate rentalDate = (rentalRequestDTO.rentalDate() != null)
                ? context.getLocalDate(rentalRequestDTO.rentalDate())
                : LocalDate.now();

        return Rental.builder()
                .rentalDate(rentalDate)
                .returnDate((rentalRequestDTO.days() != null)
                                ? rentalDate.plusDays(rentalRequestDTO.days() - 1)
                                : rentalDate.plusDays(2))
                .build();
    }

    public static RentalResponseDTO entityToDto(Rental rental) {
        System.out.println(rental.getBooks().stream().map(BookMapper::entityToDto).toList());

        return RentalResponseDTO.builder()
                .id(rental.getId())
                .rentalDate(context.getStringDate(rental.getRentalDate()))
                .returnDate(context.getStringDate(rental.getReturnDate()))
                .returned(rental.isReturned())
                .renter(RenterMapper.entityToDto(rental.getRenter()))
                .books(rental.getBooks().stream().map(BookMapper::entityToDto).toList())
                .build();
    }
}
