package com.adriankich.library.domain.service;

import com.adriankich.library.application.dto.request.BookRequestDTO;
import com.adriankich.library.application.dto.response.BookResponseDTO;
import com.adriankich.library.domain.enums.BookState;
import com.adriankich.library.domain.mapper.BookMapper;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.domain.model.Book;
import com.adriankich.library.domain.service.utilities.AuthorUtilities;
import com.adriankich.library.domain.service.utilities.BookUtilities;
import com.adriankich.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookUtilities bookUtilities;

    @Autowired
    private AuthorUtilities authorUtilities;

    public BookResponseDTO getBookById(Long id) {
        return BookMapper.entityToDto(bookUtilities.getBookById(id));
    }

    public List<BookResponseDTO> getAvailableBooks() {
        return bookUtilities.getBooksByState(BookState.AVAILABLE)
                .stream()
                .map(BookMapper::entityToDto)
                .toList();
    }

    public List<BookResponseDTO> getUnavailableBooks() {
        return bookUtilities.getBooksByState(BookState.UNAVAILABLE)
                .stream()
                .map(BookMapper::entityToDto)
                .toList();
    }

    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        List<Author> authors = authorUtilities.getAuthorsByIds(bookRequestDTO.authorsIds());

        Book book = BookMapper.dtoToEntity(bookRequestDTO);

        book.setAuthors(authors);
        book.setState(BookState.AVAILABLE);

        return BookMapper.entityToDto(bookRepository.save(book));
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = bookUtilities.getBookById(id);
        BookMapper.updateByDto(bookRequestDTO, book);

        List<Author> authors = authorUtilities.getAuthorsByIds(bookRequestDTO.authorsIds());

        book.getAuthors().clear();
        bookUtilities.addAuthorsToBook(book, authors);

        return BookMapper.entityToDto(bookRepository.save(book));
    }

    public void rentBook(Book book) {
        bookUtilities.validateBookState(book);
        book.setState(BookState.UNAVAILABLE);

        bookRepository.save(book);
    }

    public void returnBook(Book book) {
        book.setState(BookState.AVAILABLE);

        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookUtilities.getBookById(id);
        bookUtilities.validateDeletion(book);

        bookRepository.delete(book);
    }
}
