package com.adriankich.library.application.controllers;

import com.adriankich.library.application.dto.request.BookRequestDTO;
import com.adriankich.library.application.dto.response.BookResponseDTO;
import com.adriankich.library.domain.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController extends Controller {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        return ok(bookService.getBookById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookResponseDTO>> getAvailableBooks() {
        return ok(bookService.getAvailableBooks());
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<BookResponseDTO>> getUnavailableBooks() {
        return ok(bookService.getUnavailableBooks());
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
        return created(bookService.createBook(bookRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable Long id, @RequestBody @Valid BookRequestDTO bookRequestDTO) {
        return ok(bookService.updateBook(id, bookRequestDTO));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return noContent();
    }
}
