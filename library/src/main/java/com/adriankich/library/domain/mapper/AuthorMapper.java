package com.adriankich.library.domain.mapper;

import com.adriankich.library.application.dto.request.AuthorRequestDTO;
import com.adriankich.library.application.dto.response.AuthorResponseDTO;
import com.adriankich.library.domain.context.ApplicationContext;
import com.adriankich.library.domain.enums.Gender;
import com.adriankich.library.domain.model.Author;

public class AuthorMapper {
    final static ApplicationContext context = ApplicationContext.getInstance();

    public static AuthorResponseDTO entityToDto(Author author) {
        return AuthorResponseDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .gender(author.getGender().getValue())
                .birthYer(author.getBirthYear())
                .cpf(author.getCpf())
                .build();
    }

    public static Author dtoToEntity(AuthorRequestDTO authorRequestDTO) {
        return Author.builder()
                .name(authorRequestDTO.name())
                .gender(Gender.fromValue(authorRequestDTO.gender()) != null
                        ? Gender.fromValue(authorRequestDTO.gender())
                        : Gender.NOT_INFORMED)
                .birthYear(authorRequestDTO.birthYear())
                .cpf(context.formatCpf(authorRequestDTO.cpf()))
                .build();
    }

    public static void updateByDto(AuthorRequestDTO authorRequestDTO, Author author) {
        author.setName(authorRequestDTO.name());
        author.setBirthYear(authorRequestDTO.birthYear());
        author.setCpf(context.formatCpf(authorRequestDTO.cpf()));

        if(authorRequestDTO.gender() != null )
            author.setGender(Gender.fromValue(authorRequestDTO.gender()));
    }
}
