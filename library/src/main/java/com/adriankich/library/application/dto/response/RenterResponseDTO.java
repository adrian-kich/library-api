package com.adriankich.library.application.dto.response;

import lombok.Builder;

@Builder
public record RenterResponseDTO(
        Long id,
        String name,
        String gender,
        String phone,
        String email,
        String birthDate,
        String cpf
) {}
