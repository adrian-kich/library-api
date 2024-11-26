package com.adriankich.library.infrastructure.web.handler;

import com.adriankich.library.domain.exception.NotFoundException;
import com.adriankich.library.infrastructure.web.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionHandler implements ResourceExceptionHandler<NotFoundException> {

    @Override
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handle(NotFoundException ex, HttpServletRequest request) {
        return response(ex, request, HttpStatus.NOT_FOUND);
    }
}
