package com.froscii.drawing.Exceptions;

/**
 * As of 4/4/23, this should only be called if a drawing's name
 * and middle letter are not unique.
 */
public class DrawingExistsException extends RuntimeException {
    private static final long serialVersionUID = -1930795324323147289L;

    public DrawingExistsException() {
        super();
    }

    public DrawingExistsException(String message) {
        super(message);
    }

    public DrawingExistsException(Throwable cause) {
        super(cause);
    }

    public DrawingExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
