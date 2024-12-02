package com.adriankich.library.infrastructure.web.handler;

import com.adriankich.library.domain.exception.RentAlreadyReturnedException;
import com.adriankich.library.infrastructure.web.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RentAlreadyReturnedHandler implements ResourceExceptionHandler<RentAlreadyReturnedException> {

    @Override
    @ExceptionHandler(RentAlreadyReturnedException.class)
    public ResponseEntity<StandardError> handle(RentAlreadyReturnedException ex, HttpServletRequest request) {
        return response(ex, request, HttpStatus.BAD_REQUEST);
    }
}
