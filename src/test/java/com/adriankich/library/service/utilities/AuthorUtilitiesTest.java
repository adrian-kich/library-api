package com.adriankich.library.service.utilities;

import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.domain.exception.CanNotDeleteException;
import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.domain.model.Book;
import com.adriankich.library.domain.service.utilities.AuthorUtilities;
import com.adriankich.library.domain.service.utilities.BookUtilities;
import com.adriankich.library.infrastructure.repository.AuthorRepository;
import com.adriankich.library.stub.AuthorStub;
import com.adriankich.library.stub.BookStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AuthorUtilities")
public class AuthorUtilitiesTest {

    @InjectMocks
    private AuthorUtilities authorUtilities;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookUtilities bookUtilities;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("[UTILITIES] Deve encontrar um autor para o ID informado")
    public void shouldFindAuthorById() {
        Author authorStub = AuthorStub.createAuthorStub();

        when(authorRepository.findById(authorStub.getId())).thenReturn(Optional.of(authorStub));

        Author result = authorUtilities.getAuthorById(authorStub.getId());

        assertNotNull(result);
        assertEquals(authorStub.getId(), result.getId());
        assertEquals(authorStub.getName(), result.getName());
        assertEquals(authorStub.getGender(), result.getGender());
        assertEquals(authorStub.getBirthYear(), result.getBirthYear());
        assertEquals(authorStub.getCpf(), result.getCpf());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar um NotFoundException ao buscar autor para o ID informado")
    public void shouldGenerateNotFoundExceptionFindAuthorById() {
        Long findId = 1L;

        when(authorRepository.findById(findId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> authorUtilities.getAuthorById(findId));

        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals("Não foi encontrado um autor com o id: #" + findId, exception.getMessage());
    }

    @Test
    @DisplayName("[UTILITIES] Deve encontrar uma lista de autores com os IDs informados")
    public void shouldFindAuthorsByIds() {
        Author authorStub = AuthorStub.createAuthorStub();
        List<Author> authors = List.of(authorStub);

        when(authorRepository.findById(authorStub.getId())).thenReturn(Optional.of(authorStub));

        List<Author> result = authorUtilities.getAuthorsByIds(List.of(authorStub.getId()));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Author.class, result.get(0).getClass());
        assertEquals(authorStub.getId(), result.get(0).getId());
        assertEquals(authorStub.getName(), result.get(0).getName());
        assertEquals(authorStub.getGender(), result.get(0).getGender());
        assertEquals(authorStub.getBirthYear(), result.get(0).getBirthYear());
        assertEquals(authorStub.getCpf(), result.get(0).getCpf());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar um NotFoundException ao não encontrar um autor para o ID informado")
    public void shouldGenerateNotFoundExceptionFindAuthorsByIds() {
        Long findId = 1L;

        when(authorRepository.findById(findId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> authorUtilities.getAuthorsByIds(List.of(findId)));

        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals("Não foi encontrado um autor com o id: #" + findId, exception.getMessage());
    }

    @Test
    @DisplayName("[UTILITIES] Deve buscar autores pelo nome")
    public void shouldFindAuthorsByName() {
        String findName = "Adrian";
        Author authorStub = AuthorStub.createAuthorStub(1L, findName);

        when(authorRepository.findByName(findName)).thenReturn(List.of(authorStub));

        List<Author> result = authorUtilities.getAuthorsByName(findName);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Author.class, result.get(0).getClass());
        assertEquals(authorStub.getId(), result.get(0).getId());
        assertEquals(authorStub.getName(), result.get(0).getName());
        assertEquals(authorStub.getGender(), result.get(0).getGender());
        assertEquals(authorStub.getBirthYear(), result.get(0).getBirthYear());
        assertEquals(authorStub.getCpf(), result.get(0).getCpf());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar AlreadyExistsException ao encontrar cpf duplicado")
    public void shouldGenerateAlreadyExistsExceptionValidationCpf() {
        Author authorStub = AuthorStub.createAuthorStub();
        Author newAuthor = AuthorStub.createAuthorStub(2L, "New author");

        when(authorRepository.findByCpf(newAuthor.getCpf())).thenReturn(Optional.of(authorStub));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> authorUtilities.validateUniqueCpf(newAuthor));

        assertEquals("Já existe um autor cadastrado com esse CPF.", exception.getMessage());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar CanNotDeleteException ao deletar um autor")
    public void shouldGenerateCanNotDeleteExceptionValidateDeletion() {
        Author authorStub = AuthorStub.createAuthorStub();
        Book bookStub = BookStub.createBookStub();
        List<Book> books = List.of(bookStub);

        when(bookUtilities.getBooksByAuthor(authorStub)).thenReturn(books);

        CanNotDeleteException exception = assertThrows(CanNotDeleteException.class,
                () -> authorUtilities.validateDeletion(authorStub));

        assertEquals(CanNotDeleteException.class, exception.getClass());
        assertEquals(String.format("Não é possível deletar o autor #%s:%s pois o mesmo possuí livros associados.",
                authorStub.getId(), authorStub.getName()), exception.getMessage());
    }
}
