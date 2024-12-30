package com.adriankich.library.service;

import com.adriankich.library.application.dto.request.AuthorRequestDTO;
import com.adriankich.library.application.dto.request.RenterRequestDTO;
import com.adriankich.library.application.dto.response.AuthorResponseDTO;
import com.adriankich.library.application.dto.response.RenterResponseDTO;
import com.adriankich.library.domain.context.ApplicationContext;
import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Author;
import com.adriankich.library.domain.model.Renter;
import com.adriankich.library.domain.service.RenterService;
import com.adriankich.library.domain.service.utilities.RenterUtilities;
import com.adriankich.library.infrastructure.repository.RenterRepository;
import com.adriankich.library.stub.AuthorStub;
import com.adriankich.library.stub.RenterStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("RenterService")
public class RenterServiceTest {

    @InjectMocks
    private RenterService renterService;

    @Mock
    private RenterUtilities renterUtilities;

    @Mock
    private RenterRepository renterRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("[SERVICE] Deve encontrar um locatário para o ID informado")
    public void shouldFindRenterById() {
        Renter renterStub = RenterStub.createRenterStub();

        when(renterUtilities.getRenterById(renterStub.getId())).thenReturn(renterStub);

        RenterResponseDTO result = renterService.getRenterById(renterStub.getId());

        assertNotNull(result);
        assertEquals(renterStub.getId(), result.id());
        assertEquals(renterStub.getName(), result.name());
        assertEquals(renterStub.getEmail(), result.email());
        assertEquals(renterStub.getPhone(), result.phone());
        assertEquals(renterStub.getGender().getValue(), result.gender());
        assertEquals(ApplicationContext.getInstance().getStringDate(renterStub.getBirthDate()), result.birthDate());
        assertEquals(renterStub.getCpf(), result.cpf());
    }

    @Test
    @DisplayName("[SERVICE] Deve gerar um NotFoundException ao buscar locatário para o ID informado")
    public void shouldGenerateNotFoundExceptionFindRenterById() {
        Long findId = 1L;
        when(renterUtilities.getRenterById(findId)).thenThrow(
                new NotFoundException("Não foi encontrado um locatário com o id: #" + findId));

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> renterService.getRenterById(findId));

        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals("Não foi encontrado um locatário com o id: #" + findId, exception.getMessage());
    }

    @Test
    @DisplayName("[SERVICE] Deve criar um Locatário")
    public void shouldCreateRenter() {
        RenterRequestDTO renterRequestDTO = RenterStub.createRenterRequestDtoStub();
        Renter renterStub = RenterStub.createRenterStub(renterRequestDTO);

        doNothing().when(renterUtilities).validateUniqueCpf(any(Renter.class));
        doNothing().when(renterUtilities).validateUniqueEmail(any(Renter.class));
        when(renterRepository.save(renterStub)).thenReturn(renterStub);

        RenterResponseDTO result = renterService.createRenter(renterRequestDTO);

        assertNotNull(result);
        assertEquals(renterStub.getId(), result.id());
        assertEquals(renterStub.getName(), result.name());
        assertEquals(renterStub.getEmail(), result.email());
        assertEquals(renterStub.getPhone(), result.phone());
        assertEquals(renterStub.getGender().getValue(), result.gender());
        assertEquals(ApplicationContext.getInstance().getStringDate(renterStub.getBirthDate()), result.birthDate());
        assertEquals(renterStub.getCpf(), result.cpf());
        verify(renterRepository, times(1)).save(any(Renter.class));
    }
}
