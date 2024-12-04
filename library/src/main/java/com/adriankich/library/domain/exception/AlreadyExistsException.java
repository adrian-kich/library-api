package com.adriankich.library.domain.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException( String message ) {
        super( message );
    }

    public AlreadyExistsException( Throwable cause ) {
        super( cause );
    }

    public AlreadyExistsException( String message, Throwable cause ) {
        super( message, cause );
    }
}
