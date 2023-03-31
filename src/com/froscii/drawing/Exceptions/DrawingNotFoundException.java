package com.froscii.drawing.Exceptions;

public class DrawingNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -1830785223023147289L;

    public DrawingNotFoundException() {
        super();
    }

    public DrawingNotFoundException(String message) {
        super(message);
    }

    public DrawingNotFoundException(Throwable cause) {
        super(cause);
    }

    public DrawingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
