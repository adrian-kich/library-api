package com.adriankich.library.domain.validator;

import com.adriankich.library.domain.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class GenderFormatValidator implements ConstraintValidator<GenderFormat, String> {
    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        if(gender == null) return true;
        return Gender.fromValue(gender) != null;
    }
}
