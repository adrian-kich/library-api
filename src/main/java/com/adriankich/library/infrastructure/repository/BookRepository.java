package com.adriankich.library.infrastructure.repository;

import com.adriankich.library.domain.enums.BookState;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByState(BookState bookState);
    List<Book> findByAuthorsContains(Author author);
}
