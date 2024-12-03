package com.adriankich.library.domain.service.utilities;

import com.adriankich.library.domain.enums.BookState;
import com.adriankich.library.domain.exception.BookUnavailableException;
import com.adriankich.library.domain.exception.CanNotDeleteException;
import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.domain.model.Book;
import com.adriankich.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookUtilities {

    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(Long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("Não foi encontrado um livro com o id: #%s", id)));
    }

    public List<Book> getBooksByIds(List<Long> ids) {
        return ids.stream()
                .map(this::getBookById)
                .toList();
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthorsContains(author);
    }

    public List<Book> getBooksByState(BookState bookState) {
        return bookRepository.findByState(bookState);
    }

    public void addAuthorsToBook(Book book, List<Author> authors) {
        authors.forEach(book::addAuthor);
    }

    public void validateBookState(Book book) {
        if(book.getState() == BookState.UNAVAILABLE)
            throw new BookUnavailableException(
                    String.format("O livro #%s:%s não está disponível para locação", book.getId(), book.getName()));
    }

    public void validateDeletion(Book book) {
        if(book.getState().equals(BookState.UNAVAILABLE))
            throw new CanNotDeleteException(
                    String.format("Não é possível deletar o livro #%s:%s pois o mesmo está alugado."
                            , book.getId(), book.getName()));
    }
}
