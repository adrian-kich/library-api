package com.adriankich.library.stub;

import com.adriankich.library.application.dto.request.RenterRequestDTO;
import com.adriankich.library.domain.context.ApplicationContext;
import com.adriankich.library.domain.enums.Gender;
import com.adriankich.library.domain.model.Renter;

import java.time.LocalDate;

public interface RenterStub {

    static Renter createRenterStub() {
        return Renter.builder()
                .id(1L)
                .name("Adrian Kich")
                .email("ak@gmail.com")
                .cpf("03116037000")
                .phone("51 99799999")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(2001,10,26))
                .build();
    }

    static Renter createRenterStub(Long id, String name) {
        return Renter.builder()
                .id(id)
                .name(name)
                .email("ak@gmail.com")
                .cpf("03116037000")
                .phone("51 99799999")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(2001,10,26))
                .build();
    }

    static Renter createRenterStub(RenterRequestDTO renterRequestDTO) {
        return Renter.builder()
                .id(1L)
                .name(renterRequestDTO.name())
                .email(renterRequestDTO.email())
                .cpf(renterRequestDTO.cpf())
                .phone(renterRequestDTO.phone())
                .gender(Gender.fromValue(renterRequestDTO.gender()))
                .birthDate(ApplicationContext.getInstance().getLocalDate(renterRequestDTO.birthDate()))
                .build();
    }

    static RenterRequestDTO createRenterRequestDtoStub() {
        return new RenterRequestDTO(
                "Adrian Kich",
                Gender.MALE.getValue(),
                "51 989999999",
                "ak@gmail.com",
                "26-10-2001",
                "03116037000"
        );
    }
}
