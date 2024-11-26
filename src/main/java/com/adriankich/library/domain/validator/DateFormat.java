package com.adriankich.library.domain.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    String message() default "Formato de data inválido. Utilize o formato esperado: 'dd-MM-yyyy'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
