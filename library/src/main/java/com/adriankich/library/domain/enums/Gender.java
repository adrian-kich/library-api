package com.adriankich.library.domain.enums;

import java.util.Arrays;

public enum Gender {
    MALE("Masculino"),
    FEMALE("Feminino"),
    OTHER("Outro"),
    NOT_INFORMED("Não informado");

    private final String value;

    Gender(String gender) {
        this.value = gender;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
