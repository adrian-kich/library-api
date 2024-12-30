package com.adriankich.library.service.utilities;

import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.domain.exception.CanNotDeleteException;
import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Book;
import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.model.Renter;
import com.adriankich.library.domain.service.utilities.RentalUtilities;
import com.adriankich.library.domain.service.utilities.RenterUtilities;
import com.adriankich.library.infrastructure.repository.RenterRepository;
import com.adriankich.library.stub.RentalStub;
import com.adriankich.library.stub.RenterStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("RenterUtilities")
public class RenterUtilitiesTest {

    @InjectMocks
    private RenterUtilities renterUtilities;

    @Mock
    private RenterRepository renterRepository;

    @Mock
    private RentalUtilities rentalUtilities;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("[UTILITIES] Deve encontrar um locatário para o ID informado")
    public void shouldFindRenterById() {
        Renter renterStub = RenterStub.createRenterStub();

        when(renterRepository.findById(renterStub.getId())).thenReturn(Optional.of(renterStub));

        Renter result = renterUtilities.getRenterById(renterStub.getId());

        assertNotNull(result);
        assertEquals(renterStub.getId(), result.getId());
        assertEquals(renterStub.getName(), result.getName());
        assertEquals(renterStub.getEmail(), result.getEmail());
        assertEquals(renterStub.getPhone(), result.getPhone());
        assertEquals(renterStub.getGender(), result.getGender());
        assertEquals(renterStub.getBirthDate(), result.getBirthDate());
        assertEquals(renterStub.getCpf(), result.getCpf());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar um NotFoundException ao buscar locatário para o ID informado")
    public void shouldGenerateNotFoundExceptionFindAuthorById() {
        Long findId = 1L;

        when(renterRepository.findById(findId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> renterUtilities.getRenterById(findId));

        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals("Não foi encontrado um locatário com o id: #" + findId, exception.getMessage());
    }

    @Test
    @DisplayName("[UTILITIES] Deve encontrar livros alugados por um locatário")
    public void shouldFindBooksByRenter() {
        Rental rentalStub = RentalStub.createRentalStub();

        when(rentalUtilities.getRentalByRenter(rentalStub.getRenter(), false)).thenReturn(List.of(rentalStub));

        List<Book> result = renterUtilities.getBooksByRenter(rentalStub.getRenter());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Book.class, result.get(0).getClass());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar AlreadyExistsException ao encontrar cpf duplicado")
    public void shouldGenerateAlreadyExistsExceptionValidationCpf() {
        Renter renterStub = RenterStub.createRenterStub();
        Renter newRenter = RenterStub.createRenterStub(2L, "New renter");

        when(renterRepository.findByCpf(newRenter.getCpf())).thenReturn(Optional.of(renterStub));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> renterUtilities.validateUniqueCpf(newRenter));

        assertEquals("Já existe um locatário cadastrado com esse CPF.", exception.getMessage());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar AlreadyExistsException ao encontrar cpf duplicado")
    public void shouldGenerateAlreadyExistsExceptionValidationEmail() {
        Renter renterStub = RenterStub.createRenterStub();
        Renter newRenter = RenterStub.createRenterStub(2L, "New renter");

        when(renterRepository.findByEmail(newRenter.getEmail())).thenReturn(Optional.of(renterStub));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> renterUtilities.validateUniqueEmail(newRenter));

        assertEquals("Já existe um locatário cadastrado com esse E-mail.", exception.getMessage());
    }

    @Test
    @DisplayName("[UTILITIES] Deve gerar CanNotDeleteException ao deletar um locatário com livros alugados")
    public void shouldGenerateCanNotDeleteExceptionValidateDeletion() {
        Renter renterStub = RenterStub.createRenterStub();
        Rental rentalStub = RentalStub.createRentalStub();

        when(rentalUtilities.getRentalByRenter(rentalStub.getRenter(), false)).thenReturn(List.of(rentalStub));

        CanNotDeleteException exception = assertThrows(CanNotDeleteException.class,
                () -> renterUtilities.validateDeletion(renterStub));

        assertEquals(CanNotDeleteException.class, exception.getClass());
        assertEquals(String.format("Não é possível deletar o locatário #%s:%s pois o mesmo possuí livros alugados.",
                renterStub.getId(), renterStub.getName()), exception.getMessage());
    }
}
