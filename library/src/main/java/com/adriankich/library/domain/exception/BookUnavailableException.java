package com.adriankich.library.domain.exception;

public class BookUnavailableException extends RuntimeException{

    public BookUnavailableException( String message ) {
        super( message );
    }

    public BookUnavailableException( Throwable cause ) {
        super( cause );
    }

    public BookUnavailableException( String message, Throwable cause ) {
        super( message, cause );
    }
}
