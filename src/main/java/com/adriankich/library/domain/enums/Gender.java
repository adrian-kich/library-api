package com.adriankich.library.domain.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Masculino"),
    FEMALE("Feminino"),
    OTHER("Outro"),
    NOT_INFORMED("Não Informado");

    private final String value;

    Gender(String gender) {
        this.value = gender;
    }

}
