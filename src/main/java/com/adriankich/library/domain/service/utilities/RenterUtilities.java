package com.adriankich.library.domain.service.utilities;

import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.infrastructure.repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenterUtilities {

    @Autowired
    private RenterRepository renterRepository;

    public void validateUniqueCpf(String cpf) {
        if(renterRepository.existsByCpf(cpf))
            throw new AlreadyExistsException("Já existe um locatário cadastrado com esse CPF.");
    }

}
