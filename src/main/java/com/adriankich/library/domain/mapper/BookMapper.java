package com.adriankich.library.domain.mapper;

import com.adriankich.library.application.dto.request.BookRequestDTO;
import com.adriankich.library.application.dto.response.BookResponseDTO;
import com.adriankich.library.domain.context.ApplicationContext;
import com.adriankich.library.domain.model.Book;

public class BookMapper {
    final static ApplicationContext context = ApplicationContext.getInstance();

    public static BookResponseDTO entityToDto(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .isbn(book.getIsbn())
                .publishDate(context.getStringDate(book.getPublishDate()))
                .state(book.getState())
                .authors(book.getAuthors().stream().map(AuthorMapper::entityToDto).toList())
                .build();
    }

    public static Book dtoToEntity(BookRequestDTO bookRequestDTO) {
        return Book.builder()
                .name(bookRequestDTO.name())
                .isbn(bookRequestDTO.isbn())
                .publishDate(context.getLocalDate(bookRequestDTO.publishDate()))
                .build();
    }

    public static void updateByDto(BookRequestDTO bookRequestDTO, Book book) {
        book.setName(bookRequestDTO.name());
        book.setIsbn(bookRequestDTO.isbn());
        book.setPublishDate(context.getLocalDate(bookRequestDTO.publishDate()));
    }
}
