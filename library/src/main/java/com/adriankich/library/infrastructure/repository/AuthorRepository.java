package com.adriankich.library.infrastructure.repository;

import com.adriankich.library.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByCpf(String cpf);
    List<Author> findByName(String name);
}
