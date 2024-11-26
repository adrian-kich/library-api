package com.adriankich.library.application.dto.request;

import com.adriankich.library.domain.validator.DateFormat;
import com.adriankich.library.domain.validator.GenderFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record RenterRequestDTO(
        @NotBlank(message = "O nome do locatário deve ser informado.")
        String name,

        @GenderFormat
        String gender,

        @NotBlank(message = "O telefone do locatário deve ser informado.")
        String phone,

        @Email(message = "Email inválido.")
        @NotBlank(message = "O email do locatário deve ser informado.")
        String email,

        @DateFormat
        @NotBlank(message = "A data de nascimento do locatário deve ser informada.")
        String birthDate,

        @CPF(message = "CPF inválido")
        @NotBlank(message = "O CPF do locatário deve ser informado.")
        String cpf
) {}
