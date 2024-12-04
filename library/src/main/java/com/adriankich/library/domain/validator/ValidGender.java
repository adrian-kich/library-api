package com.adriankich.library.domain.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {
    String message() default "O sexo deve ser Masculino, Feminino ou Outro.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
