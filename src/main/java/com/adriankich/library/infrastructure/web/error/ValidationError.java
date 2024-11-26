package com.adriankich.library.infrastructure.web.error;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ValidationError extends StandardError {
    private String fields;
    private String fieldsMessage;
}
