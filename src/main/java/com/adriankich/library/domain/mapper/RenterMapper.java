package com.adriankich.library.domain.mapper;

import com.adriankich.library.application.dto.request.RenterRequestDTO;
import com.adriankich.library.application.dto.response.RenterResponseDTO;
import com.adriankich.library.domain.context.ApplicationContext;
import com.adriankich.library.domain.enums.Gender;
import com.adriankich.library.domain.model.Renter;

public class RenterMapper {
    final static ApplicationContext context = ApplicationContext.getInstance();

    public static RenterResponseDTO entityToDto(Renter renter) {
        return RenterResponseDTO.builder()
                .id(renter.getId())
                .name(renter.getName())
                .gender(renter.getGender().getValue())
                .phone(renter.getPhone())
                .email(renter.getEmail())
                .birthDate(context.getStringDate(renter.getBirthDate()))
                .cpf(renter.getCpf())
                .build();
    }

    public static Renter dtoToEntity(RenterRequestDTO renterRequestDTO) {
        return Renter.builder()
                .name(renterRequestDTO.name())
                .gender(Gender.fromValue(renterRequestDTO.gender()) != null
                        ? Gender.fromValue(renterRequestDTO.gender())
                        : Gender.NOT_INFORMED)
                .phone(renterRequestDTO.phone())
                .email(renterRequestDTO.email())
                .birthDate(context.getLocalDate(renterRequestDTO.birthDate()))
                .cpf(context.formatCpf(renterRequestDTO.cpf()))
                .build();
    }
}
