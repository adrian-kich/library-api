package com.adriankich.library.domain.exception;

public class RentAlreadyReturnedException extends RuntimeException{

    public RentAlreadyReturnedException(String message ) {
        super( message );
    }

    public RentAlreadyReturnedException(Throwable cause ) {
        super( cause );
    }

    public RentAlreadyReturnedException(String message, Throwable cause ) {
        super( message, cause );
    }
}
