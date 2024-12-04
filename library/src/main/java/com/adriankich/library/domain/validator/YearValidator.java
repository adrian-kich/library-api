package com.adriankich.library.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class YearValidator implements ConstraintValidator<ValidYear, Integer> {
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = LocalDate.now().getYear();

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if(year > LocalDate.now().getYear()) {
            addConstraintViolation(context, "O ano deve ser anterior ao atual.");
            return false;
        }

        if(year < MIN_YEAR || year > MAX_YEAR) {
            addConstraintViolation(context,
                    String.format("O ano deve ser informado com no formato yyyy, entre 1900 e %s.", MAX_YEAR));
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
