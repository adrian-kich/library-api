package com.adriankich.library.infrastructure.web.handler;

import com.adriankich.library.infrastructure.web.error.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler implements ResourceExceptionHandler<MethodArgumentNotValidException>{

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handle(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return responseValidation(ex, request);
    }

    private ResponseEntity<ValidationError> responseValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request ) {
        String ERROR_MESSAGE = "Erro na validação dos campos";

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationError error = ValidationError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ERROR_MESSAGE)
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
