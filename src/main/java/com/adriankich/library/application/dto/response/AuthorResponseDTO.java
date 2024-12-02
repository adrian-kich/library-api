package com.adriankich.library.application.dto.response;

import lombok.Builder;

@Builder
public record AuthorResponseDTO(
    Long id,
    String name,
    String gender,
    int birthYer,
    String cpf
) {}
