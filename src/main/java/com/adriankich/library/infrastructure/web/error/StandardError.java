package com.adriankich.library.infrastructure.web.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class StandardError {
    protected LocalDateTime timestamp;
    protected Integer status;
    protected String error;
    protected String path;
}
