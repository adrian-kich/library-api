package com.adriankich.library.service;

import com.adriankich.library.application.dto.request.AuthorRequestDTO;
import com.adriankich.library.application.dto.response.AuthorResponseDTO;
import com.adriankich.library.application.dto.response.BookResponseDTO;
import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.domain.exception.CanNotDeleteException;
import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.domain.model.Book;
import com.adriankich.library.domain.service.AuthorService;
import com.adriankich.library.domain.service.utilities.AuthorUtilities;
import com.adriankich.library.domain.service.utilities.BookUtilities;
import com.adriankich.library.infrastructure.repository.AuthorRepository;
import com.adriankich.library.stub.AuthorStub;
import com.adriankich.library.stub.BookStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("AuthorService")
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
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
    @DisplayName("[SERVICE] Deve encontrar um autor para o ID informado")
    public void shouldFindAuthorById() {
        Author authorStub = AuthorStub.createAuthorStub();

        when(authorUtilities.getAuthorById(authorStub.getId())).thenReturn(authorStub);

        AuthorResponseDTO result = authorService.getAuthorById(authorStub.getId());

        assertNotNull(result);
        assertEquals(result.id(), authorStub.getId());
        assertEquals(result.name(), authorStub.getName());
        assertEquals(result.gender(), authorStub.getGender().getValue());
        assertEquals(result.birthYer(), authorStub.getBirthYear());
        assertEquals(result.cpf(), authorStub.getCpf());
    }

    @Test
    @DisplayName("[SERVICE] Deve gerar um NotFoundException ao buscar autor para o ID informado")
    public void shouldGenerateNotFoundExceptionFindAuthorById() {
        Long findId = 1L;
        when(authorUtilities.getAuthorById(anyLong())).thenThrow(
                new NotFoundException("Não foi encontrado um autor com o id: #" + findId));

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> authorService.getAuthorById(findId));

        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals("Não foi encontrado um autor com o id: #" + findId, exception.getMessage());
    }

    @Test
    @DisplayName("[SERVICE] Deve encontrar autores pelo nome")
    public void shouldFindAuthorsByName() {
        String findName = "Adrian";

        Author authorStub = AuthorStub.createAuthorStub(1L, "Adrian Kich");
        Author authorStub2 = AuthorStub.createAuthorStub(2L, "Adrian Gabriel");
        List<Author> authors = List.of(authorStub, authorStub2);

        when(authorUtilities.getAuthorsByName(findName)).thenReturn(authors);

        List<AuthorResponseDTO> result = authorService.getAuthorsByName(findName);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0).name().contains(findName));
        assertTrue(result.get(1).name().contains(findName));
    }

    @Test
    @DisplayName("[SERVICE] Deve criar um autor")
    public void shouldCreateAuthor() {
        AuthorRequestDTO authorRequestDTO = AuthorStub.createAuthorRequestDtoStub();
        Author authorStub = AuthorStub.createAuthorStub(authorRequestDTO);

        when(authorRepository.save(any())).thenReturn(authorStub);

        AuthorResponseDTO result = authorService.createAuthor(authorRequestDTO);

        assertNotNull(result);
        assertEquals(result.id(), authorStub.getId());
        assertEquals(result.name(), authorStub.getName());
        assertEquals(result.gender(), authorStub.getGender().getValue());
        assertEquals(result.birthYer(), authorStub.getBirthYear());
        assertEquals(result.cpf(), authorStub.getCpf());
        verify(authorRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("[SERVICE] Deve gerar um AlreadyExistsException ao criar um autor")
    public void shouldGenerateAlreadyExistsExceptionCreateAuthor() {
        AuthorRequestDTO authorRequestDTO = AuthorStub.createAuthorRequestDtoStub();

        doThrow(new AlreadyExistsException("Já existe um autor cadastrado com esse CPF."))
                .when(authorUtilities).validateUniqueCpf(any());

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> authorService.createAuthor(authorRequestDTO));

        assertEquals("Já existe um autor cadastrado com esse CPF.", exception.getMessage());
        verify(authorUtilities, times(1)).validateUniqueCpf(any(Author.class));
    }

    @Test
    @DisplayName("[SERVICE] Deve atualizar dados de um autor")
    public void shouldUpdateAuthor() {
        AuthorRequestDTO authorRequestDTO = AuthorStub.createAuthorRequestDtoStub("Author renamed");
        Author existingAuthor = AuthorStub.createAuthorStub();

        when(authorUtilities.getAuthorById(existingAuthor.getId())).thenReturn(existingAuthor);
        when(authorRepository.save(any(Author.class))).thenReturn(existingAuthor);

        AuthorResponseDTO result = authorService.updateAuthor(existingAuthor.getId(), authorRequestDTO);

        assertNotNull(result);
        assertEquals(existingAuthor.getId(), result.id());
        assertEquals(existingAuthor.getName(), result.name());
        assertEquals(existingAuthor.getGender().getValue(), result.gender());
        assertEquals(existingAuthor.getBirthYear(), result.birthYer());
        assertEquals(existingAuthor.getCpf(), result.cpf());
        verify(authorRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("[SERVICE] Deve gerar um AlreadyExistsException ao criar um autor")
    public void shouldGenerateAlreadyExistsExceptionUpdateAuthor() {
        AuthorRequestDTO authorRequestDTO = AuthorStub.createAuthorRequestDtoStub("Author renamed");
        Author existingAuthor = AuthorStub.createAuthorStub();

        when(authorUtilities.getAuthorById(existingAuthor.getId())).thenReturn(existingAuthor);

        doThrow(new AlreadyExistsException("Já existe um autor cadastrado com esse CPF."))
                .when(authorUtilities).validateUniqueCpf(any());

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> authorService.updateAuthor(existingAuthor.getId(), authorRequestDTO));

        assertEquals("Já existe um autor cadastrado com esse CPF.", exception.getMessage());
        verify(authorUtilities, times(1)).validateUniqueCpf(any(Author.class));
    }

    @Test
    @DisplayName("[SERVICE] Deve gerar um NotFoundException ao atualizar um autor inexistente")
    public void shouldGenerateNotFoundExceptionUpdateAuthor() {
        Long findId = 1L;
        AuthorRequestDTO authorRequestDTO = AuthorStub.createAuthorRequestDtoStub("Author renamed");

        when(authorUtilities.getAuthorById(findId)).thenThrow(
                new NotFoundException("Não foi encontrado um autor com o id: #" + findId));

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> authorService.updateAuthor(findId, authorRequestDTO));

        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals("Não foi encontrado um autor com o id: #" + findId, exception.getMessage());
    }

    @Test
    @DisplayName("[SERVICE] Deve deletar um autor")
    public void shouldDeleteAuthor() {
        Author authorStub = AuthorStub.createAuthorStub();

        when(authorUtilities.getAuthorById(authorStub.getId())).thenReturn(authorStub);

        authorService.deleteAuthor(authorStub.getId());

        verify(authorRepository, times(1)).delete(any(Author.class));
    }

    @Test
    @DisplayName("[SERVICE] Deve gerar um NotFoundException ao deletar um autor")
    public void shouldGenerateNotFoundExceptionDeleteAuthor() {
        Long findId = 1L;

        when(authorUtilities.getAuthorById(findId)).thenThrow(
                new NotFoundException("Não foi encontrado um autor com o id: #" + findId));

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> authorService.deleteAuthor(findId));

        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals("Não foi encontrado um autor com o id: #" + findId, exception.getMessage());
        verify(authorRepository, times(0)).delete(any(Author.class));
    }

    @Test
    @DisplayName("[SERVICE] Deve gerar um CanNotDeleteException ao tentar deletar um autor")
    public void shouldGenerateCanNotDeleteExceptionDeleteAuthor() {
        Author authorStub = AuthorStub.createAuthorStub();

        when(authorUtilities.getAuthorById(authorStub.getId())).thenReturn(authorStub);

        doThrow(new CanNotDeleteException(
                String.format("Não é possível deletar o autor #%s:%s pois o mesmo possuí livros associados."
                        , authorStub.getId(), authorStub.getName())))
                .when(authorUtilities).validateDeletion(any(Author.class));

        CanNotDeleteException exception = assertThrows(CanNotDeleteException.class,
                () -> authorService.deleteAuthor(authorStub.getId()));

        assertEquals(CanNotDeleteException.class, exception.getClass());
        assertEquals(String.format("Não é possível deletar o autor #%s:%s pois o mesmo possuí livros associados.",
                authorStub.getId(), authorStub.getName()), exception.getMessage());
        verify(authorRepository, times(0)).delete(any(Author.class));
    }

    @Test
    @DisplayName("[SERVICE] Deve buscar livros de acordo com o autor")
    public void shouldFindBooksByAuthor() {
        Author authorStub = AuthorStub.createAuthorStub();
        Book bookStub = BookStub.createBookStub();
        List<Book> books = List.of(bookStub);

        when(authorUtilities.getAuthorById(authorStub.getId())).thenReturn(authorStub);
        when(bookUtilities.getBooksByAuthor(any(Author.class))).thenReturn(books);

        List<BookResponseDTO> result = authorService.getBooksByAuthor(authorStub.getId());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(BookResponseDTO.class, result.get(0).getClass());
    }
}
