package com.froscii.drawing.Exceptions;

public class CollectionExistsException extends RuntimeException{
    private static final long serialVersionUID = -1830795223023147289L;

    public CollectionExistsException() {
        super();
    }

    public CollectionExistsException(String message) {
        super(message);
    }

    public CollectionExistsException(Throwable cause) {
        super(cause);
    }

    public CollectionExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
