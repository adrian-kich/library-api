package com.adriankich.library.application.dto.request;

import com.adriankich.library.domain.validator.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BookRequestDTO(
        @NotBlank(message = "O nome do livro deve ser informado.")
        String name,

        @NotBlank(message = "O ISBN deve ser informado.")
        String isbn,

        @ValidDate
        @NotBlank(message = "A data de publicação deve ser informada.")
        String publishDate,

        @NotNull(message = "É preciso informar ao menos um autor para o livro.")
        @NotEmpty(message = "É preciso informar ao menos um autor para o livro.")
        List<Long> authorsIds
) {}
