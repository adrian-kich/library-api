package com.adriankich.library.infrastructure.web.handler;

import com.adriankich.library.domain.exception.AlreadyExistsException;
import com.adriankich.library.infrastructure.web.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler implements ResourceExceptionHandler<RuntimeException> {

    @Override
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> handle(RuntimeException ex, HttpServletRequest request) {
        String ERROR_MESSAGE = "Erro não tratado no servidor, contate um administrador.";
        return response(ERROR_MESSAGE, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
