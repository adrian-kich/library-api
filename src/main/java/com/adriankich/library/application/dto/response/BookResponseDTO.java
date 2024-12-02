package com.adriankich.library.application.dto.response;

import com.adriankich.library.domain.enums.BookState;
import lombok.Builder;

import java.util.List;

@Builder
public record BookResponseDTO(
        Long id,
        String name,
        String isbn,
        String publishDate,
        BookState state,
        List<AuthorResponseDTO> authors
) {}
