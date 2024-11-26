package com.adriankich.library.domain.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderFormatValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface GenderFormat {
    String message() default "O sexo deve ser Masculino, Feminino ou Outro.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
