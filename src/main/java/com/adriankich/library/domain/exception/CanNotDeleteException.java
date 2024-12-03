package com.adriankich.library.domain.exception;

public class CanNotDeleteException extends RuntimeException{

    public CanNotDeleteException( String message ) {
        super( message );
    }

    public CanNotDeleteException( Throwable cause ) {
        super( cause );
    }

    public CanNotDeleteException( String message, Throwable cause ) {
        super( message, cause );
    }
}
