package com.adriankich.library.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, FORMATTER);

            if (parsedDate.isAfter(LocalDate.now())) {
                addConstraintViolation(context, "A data deve ser anterior à data atual.");
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            addConstraintViolation(context, "Formato de data inválido. Use o formato dd-MM-yyyy.");
            return false;
        }
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
