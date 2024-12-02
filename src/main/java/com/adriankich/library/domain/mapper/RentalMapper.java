package com.adriankich.library.domain.mapper;

import com.adriankich.library.application.dto.request.RentalRequestDTO;
import com.adriankich.library.application.dto.response.RentalResponseDTO;
import com.adriankich.library.domain.context.ApplicationContext;
import com.adriankich.library.domain.model.Rental;

import java.time.LocalDate;

public class RentalMapper {
    final static ApplicationContext context = ApplicationContext.getInstance();

    public static Rental dtoToEntity(RentalRequestDTO rentalRequestDTO) {
        return Rental.builder()
                .rentalDate((rentalRequestDTO.rentalDate() != null)
                                ? context.getLocalDate(rentalRequestDTO.rentalDate())
                                : LocalDate.now())
                .returnDate((rentalRequestDTO.days() != null)
                                ? LocalDate.now().plusDays(rentalRequestDTO.days())
                                : LocalDate.now().plusDays(2))
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
