package com.adriankich.library.application.dto.request;

import com.adriankich.library.domain.validator.ValidDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RentalRequestDTO(
        @ValidDate
        String rentalDate,

        @Min(value = 0, message = "O número de dias deve ser superior a 0.")
        Long days,

        @NotNull(message = "O locatário deve ser informado.")
        Long renterId,

        @NotNull(message = "É preciso informar ao menos um livro.")
        @NotEmpty(message = "É preciso informar ao menos um livro.")
        List<Long> booksIds
) {}
