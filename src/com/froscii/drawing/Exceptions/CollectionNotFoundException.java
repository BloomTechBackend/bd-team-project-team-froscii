package com.froscii.drawing.Exceptions;

public class CollectionNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -1830685223023147279L;

    public CollectionNotFoundException() {
        super();
    }

    public CollectionNotFoundException(String message) {
        super(message);
    }

    public CollectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public CollectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
