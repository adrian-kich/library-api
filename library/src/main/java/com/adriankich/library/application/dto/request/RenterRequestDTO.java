package com.adriankich.library.application.dto.request;

import com.adriankich.library.domain.validator.ValidDate;
import com.adriankich.library.domain.validator.ValidGender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record RenterRequestDTO(
        @NotBlank(message = "O nome do locatário deve ser informado.")
        String name,

        @ValidGender
        String gender,

        @NotBlank(message = "O telefone do locatário deve ser informado.")
        String phone,

        @Email(message = "Email inválido.")
        @NotBlank(message = "O email do locatário deve ser informado.")
        String email,

        @ValidDate
        @NotBlank(message = "A data de nascimento do locatário deve ser informada.")
        String birthDate,

        @CPF(message = "CPF inválido")
        @NotBlank(message = "O CPF do locatário deve ser informado.")
        String cpf
) {}
