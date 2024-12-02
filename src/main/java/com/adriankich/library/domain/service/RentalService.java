package com.adriankich.library.domain.service;

import com.adriankich.library.application.dto.request.RentalRequestDTO;
import com.adriankich.library.application.dto.response.RentalResponseDTO;
import com.adriankich.library.domain.exception.RentAlreadyReturnedException;
import com.adriankich.library.domain.mapper.RentalMapper;
import com.adriankich.library.domain.mapper.RenterMapper;
import com.adriankich.library.domain.model.Book;
import com.adriankich.library.domain.model.Rental;
import com.adriankich.library.domain.model.Renter;
import com.adriankich.library.domain.service.utilities.BookUtilities;
import com.adriankich.library.domain.service.utilities.RentalUtilities;
import com.adriankich.library.domain.service.utilities.RenterUtilities;
import com.adriankich.library.infrastructure.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalUtilities rentalUtilities;

    @Autowired
    private RenterUtilities renterUtilities;

    @Autowired
    private BookUtilities bookUtilities;

    @Autowired
    private BookService bookService;

    public RentalResponseDTO getRentalById(Long id) {
        return RentalMapper.entityToDto(rentalUtilities.getRentalById(id));
    }

    public RentalResponseDTO createRental(RentalRequestDTO rentalRequestDTO) {
        Rental rental = RentalMapper.dtoToEntity(rentalRequestDTO);
        rental.setReturned(false);

        Renter renter = renterUtilities.getRenterById(rentalRequestDTO.renterId());
        List<Book> books = bookUtilities.getBooksByIds(rentalRequestDTO.booksIds());

        books.forEach(book -> bookService.rentBook(book));

        rental.setRenter(renter);
        rental.setBooks(books);

        return RentalMapper.entityToDto(rentalRepository.save(rental));
    }

    public RentalResponseDTO returnsBooks(Long id) {
        Rental rental = rentalUtilities.getRentalById(id);

        if(rental.isReturned())
            throw new RentAlreadyReturnedException("Os livros desse aluguel já foram retornados");

        rental.getBooks().forEach(bookService::returnBook);
        rental.setReturned(true);

        return RentalMapper.entityToDto(rentalRepository.save(rental));
    }
}
