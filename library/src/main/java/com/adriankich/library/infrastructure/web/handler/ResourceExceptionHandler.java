package com.adriankich.library.infrastructure.web.handler;

import com.adriankich.library.infrastructure.web.error.StandardError;
import com.adriankich.library.infrastructure.web.error.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public interface ResourceExceptionHandler<T extends Exception> {
    public ResponseEntity<?> handle(T ex, HttpServletRequest request );

    public default ResponseEntity<StandardError> response(Exception ex, HttpServletRequest request, HttpStatus status ) {
        return response(ex.getMessage(), request, status);
    }

    public default ResponseEntity<StandardError> response(String message, HttpServletRequest request, HttpStatus status ) {
        StandardError error = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(message)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(error);
    }
}
