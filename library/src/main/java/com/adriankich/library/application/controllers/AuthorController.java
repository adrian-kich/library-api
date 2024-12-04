package com.adriankich.library.application.controllers;

import com.adriankich.library.application.dto.request.AuthorRequestDTO;
import com.adriankich.library.application.dto.response.AuthorResponseDTO;
import com.adriankich.library.application.dto.response.BookResponseDTO;
import com.adriankich.library.domain.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController extends Controller {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        return ok(authorService.getAuthorById(id));
    }

    @GetMapping()
    public ResponseEntity<List<AuthorResponseDTO>> getAuthorsByName(@RequestParam(required = false) String name) {
        return ok(authorService.getAuthorsByName(name));
    }

    @PostMapping()
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody @Valid AuthorRequestDTO authorRequestDTO) {
        return created(authorService.createAuthor(authorRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(
            @PathVariable Long id, @RequestBody @Valid AuthorRequestDTO authorRequestDTO) {
        return ok(authorService.updateAuthor(id, authorRequestDTO));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthor(@PathVariable Long id) {
        return ok(authorService.getBooksByAuthor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return noContent();
    }
}
