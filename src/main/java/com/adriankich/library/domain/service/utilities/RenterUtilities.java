package com.adriankich.library.domain.service.utilities;

import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.domain.model.Renter;
import com.adriankich.library.infrastructure.repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RenterUtilities {

    @Autowired
    private RenterRepository renterRepository;

    public Renter getRenterById(Long id) {
        return renterRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("Não foi encontrado um locatário com o id: #%s", id)));
    }

    public void validateUniqueCpf(Renter renter) {
        renterRepository.findByCpf(renter.getCpf()).ifPresent(existingRenter -> {
            if (!existingRenter.getId().equals(renter.getId())) {
                throw new AlreadyExistsException("Já existe um locatário cadastrado com esse CPF.");
            }
        });
    }

    public void validateUniqueEmail(Renter renter) {
        renterRepository.findByEmail(renter.getEmail()).ifPresent(existingRenter -> {
            if (!existingRenter.getId().equals(renter.getId())) {
                throw new AlreadyExistsException("Já existe um locatário cadastrado com esse E-mail.");
            }
        });
    }
}
