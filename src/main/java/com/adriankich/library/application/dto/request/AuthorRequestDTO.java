package com.adriankich.library.application.dto.request;

import com.adriankich.library.domain.validator.ValidGender;
import com.adriankich.library.domain.validator.ValidYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public record AuthorRequestDTO(
        @NotBlank(message = "O nome do autor deve ser informado.")
        String name,

        @ValidGender
        String gender,

        @ValidYear
        @NotNull(message = "O ano é obrigatório.")
        int birthYear,

        @CPF(message = "CPF inválido.")
        @NotBlank(message = "O CPF deve ser informado.")
        String cpf
) {}
