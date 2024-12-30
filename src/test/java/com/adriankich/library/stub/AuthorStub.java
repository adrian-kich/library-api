package com.adriankich.library.stub;

import com.adriankich.library.application.dto.request.AuthorRequestDTO;
import com.adriankich.library.domain.enums.Gender;
import com.adriankich.library.domain.model.Author;

public interface AuthorStub {

    static Author createAuthorStub() {
        return Author.builder()
                .id(1L)
                .name("Adrian Kich")
                .gender(Gender.MALE)
                .birthYear(2001)
                .cpf("03116037000")
                .build();
    }

    static Author createAuthorStub(Long id, String name) {
        return Author.builder()
                .id(id)
                .name(name)
                .gender(Gender.MALE)
                .birthYear(2001)
                .cpf("03116037000")
                .build();
    }

    static Author createAuthorStub(AuthorRequestDTO authorRequestDTO) {
        return Author.builder()
                .id(1L)
                .name(authorRequestDTO.name())
                .gender(Gender.fromValue(authorRequestDTO.gender()))
                .birthYear(authorRequestDTO.birthYear())
                .cpf(authorRequestDTO.cpf())
                .build();
    }

    static Author createAuthorStubWithoutId(AuthorRequestDTO authorRequestDTO) {
        return Author.builder()
                .name(authorRequestDTO.name())
                .gender(Gender.fromValue(authorRequestDTO.gender()))
                .birthYear(authorRequestDTO.birthYear())
                .cpf(authorRequestDTO.cpf())
                .build();
    }

    static AuthorRequestDTO createAuthorRequestDtoStub() {
        return AuthorRequestDTO.builder()
                .name("Adrian Kich")
                .gender(Gender.MALE.getValue())
                .birthYear(2001)
                .cpf("03116037000")
                .build();
    }

    static AuthorRequestDTO createAuthorRequestDtoStub(String name) {
        return AuthorRequestDTO.builder()
                .name(name)
                .gender(Gender.MALE.getValue())
                .birthYear(2001)
                .cpf("03116037000")
                .build();
    }
}
