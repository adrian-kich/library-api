package com.adriankich.library.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Controller {

    protected <T> ResponseEntity<T> created(T body ) {
        return new ResponseEntity<>( body, HttpStatus.CREATED );
    }

    protected <T> ResponseEntity<T> ok( T body ) {
        return new ResponseEntity<>( body, HttpStatus.OK );
    }

    protected <T> ResponseEntity<T> noContent() {
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
}
