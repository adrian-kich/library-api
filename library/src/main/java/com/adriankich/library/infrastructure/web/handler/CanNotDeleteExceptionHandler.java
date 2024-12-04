package com.adriankich.library.infrastructure.web.handler;

import com.adriankich.library.domain.exception.CanNotDeleteException;
import com.adriankich.library.infrastructure.web.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CanNotDeleteExceptionHandler implements ResourceExceptionHandler<CanNotDeleteException> {

    @Override
    @ExceptionHandler(CanNotDeleteException.class)
    public ResponseEntity<StandardError> handle(CanNotDeleteException ex, HttpServletRequest request) {
        return response(ex, request, HttpStatus.BAD_REQUEST);
    }
}
