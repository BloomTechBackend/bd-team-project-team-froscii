package com.froscii.drawing.Exceptions;

public class DrawingCollectionNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -1830685223023147279L;

    public DrawingCollectionNotFoundException() {
        super();
    }

    public DrawingCollectionNotFoundException(String message) {
        super(message);
    }

    public DrawingCollectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public DrawingCollectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
