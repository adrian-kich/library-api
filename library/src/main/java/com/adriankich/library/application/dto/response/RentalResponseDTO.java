package com.adriankich.library.application.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RentalResponseDTO(
        Long id,
        String rentalDate,
        String returnDate,
        boolean returned,
        RenterResponseDTO renter,
        List<BookResponseDTO> books
) {}
