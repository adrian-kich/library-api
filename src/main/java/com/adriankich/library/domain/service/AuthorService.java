package com.adriankich.library.domain.service;

import com.adriankich.library.application.dto.request.AuthorRequestDTO;
import com.adriankich.library.application.dto.response.AuthorResponseDTO;
import com.adriankich.library.application.dto.response.BookResponseDTO;
import com.adriankich.library.domain.mapper.AuthorMapper;
import com.adriankich.library.domain.mapper.BookMapper;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.domain.service.utilities.AuthorUtilities;
import com.adriankich.library.domain.service.utilities.BookUtilities;
import com.adriankich.library.infrastructure.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorUtilities authorUtilities;

    @Autowired
    private BookUtilities bookUtilities;

    public AuthorResponseDTO getAuthorById(Long id) {
        return AuthorMapper.entityToDto(authorUtilities.getAuthorById(id));
    }

    public List<AuthorResponseDTO> getAuthorsByName(String name) {
        return authorUtilities.getAuthorsByName(name)
                .stream()
                .map(AuthorMapper::entityToDto)
                .toList();
    }

    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = AuthorMapper.dtoToEntity(authorRequestDTO);

        authorUtilities.validateUniqueCpf(author);

        return AuthorMapper.entityToDto(authorRepository.save(author));
    }

    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author = authorUtilities.getAuthorById(id);
        AuthorMapper.updateByDto(authorRequestDTO, author);

        authorUtilities.validateUniqueCpf(author);

        return AuthorMapper.entityToDto(authorRepository.save(author));
    }

    public List<BookResponseDTO> getBooksByAuthor(Long authorId) {
        Author author = authorUtilities.getAuthorById(authorId);

        return bookUtilities.getBooksByAuthor(author)
                .stream()
                .map(BookMapper::entityToDto)
                .toList();
    }

    public void deleteAuthor(Long id) {
        Author author = authorUtilities.getAuthorById(id);
        authorUtilities.validateDeletion(author);

        authorRepository.delete(author);
    }
}
