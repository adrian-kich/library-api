package com.adriankich.library.stub;

import com.adriankich.library.domain.enums.BookState;
import com.adriankich.library.domain.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookStub {

    static Book createBookStub() {
        return Book.builder()
                .id(1L)
                .name("Mais Esperto que o Diabo")
                .isbn("9783959720793")
                .publishDate(LocalDate.now())
                .authors(List.of(AuthorStub.createAuthorStub()))
                .state(BookState.AVAILABLE)
                .build();
    }
}
