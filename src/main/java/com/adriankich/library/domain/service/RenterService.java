package com.adriankich.library.domain.service;

import com.adriankich.library.application.dto.request.RenterRequestDTO;
import com.adriankich.library.application.dto.response.BookResponseDTO;
import com.adriankich.library.application.dto.response.RenterResponseDTO;
import com.adriankich.library.domain.enums.Gender;
import com.adriankich.library.domain.mapper.BookMapper;
import com.adriankich.library.domain.mapper.RenterMapper;
import com.adriankich.library.domain.model.Book;
import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.model.Renter;
import com.adriankich.library.domain.service.utilities.RentalUtilities;
import com.adriankich.library.domain.service.utilities.RenterUtilities;
import com.adriankich.library.infrastructure.repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenterService {

    @Autowired
    private RenterRepository renterRepository;

    @Autowired
    private RenterUtilities renterUtilities;

    @Autowired
    private RentalUtilities rentalUtilities;

    public RenterResponseDTO getRenterById(Long id) {
        return RenterMapper.entityToDto(renterUtilities.getRenterById(id));
    }

    public RenterResponseDTO createRenter(RenterRequestDTO renterRequestDTO) {
        Renter renter = RenterMapper.dtoToEntity(renterRequestDTO);

        renterUtilities.validateUniqueCpf(renter);
        renterUtilities.validateUniqueEmail(renter);

        return RenterMapper.entityToDto(renterRepository.save(renter));
    }

    public RenterResponseDTO updateRenter(Long id, RenterRequestDTO renterRequestDTO) {
        Renter renter = renterUtilities.getRenterById(id);
        RenterMapper.updateByDto(renterRequestDTO, renter);

        renterUtilities.validateUniqueCpf(renter);
        renterUtilities.validateUniqueEmail(renter);

        return RenterMapper.entityToDto(renterRepository.save(renter));
    }

    public List<BookResponseDTO> getBooksByRenter(Long renterId) {
        Renter renter = renterUtilities.getRenterById(renterId);

        List<Book> books = renterUtilities.getBooksByRenter(renter);

        return books.stream().map(BookMapper::entityToDto).toList();
    }
}