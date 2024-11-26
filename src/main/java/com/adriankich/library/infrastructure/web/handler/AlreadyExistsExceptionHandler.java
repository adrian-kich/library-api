package com.adriankich.library.infrastructure.web.handler;

import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.infrastructure.web.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlreadyExistsExceptionHandler implements ResourceExceptionHandler<AlreadyExistsException> {

    @Override
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardError> handle(AlreadyExistsException ex, HttpServletRequest request) {
        return response(ex, request, HttpStatus.BAD_REQUEST);
    }
}
