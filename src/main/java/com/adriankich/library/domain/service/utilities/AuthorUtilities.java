package com.adriankich.library.domain.service.utilities;

import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.infrastructure.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorUtilities {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthorById(Long id) {
        return authorRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("Não foi encontrado um autor com o id: #%s", id)));
    }

    public List<Author> getAuthorsByIds(List<Long> ids) {
        return ids.stream()
                .map(this::getAuthorById)
                .toList();
    }

    public List<Author> getAuthorsByName(String name) {
        return authorRepository.findByName(name);
    }

    public void validateUniqueCpf(Author author) {
        authorRepository.findByCpf(author.getCpf()).ifPresent(existingRenter -> {
            if (!existingRenter.getId().equals(author.getId())) {
                throw new AlreadyExistsException("Já existe um autor cadastrado com esse CPF.");
            }
        });
    }
}
