package com.amazon.ata.music.playlist.service.exceptions;

public class InvalidAttributeChangeException extends InvalidAttributeException {

    public InvalidAttributeChangeException() {
        super();
    }
    public InvalidAttributeChangeException(String message) {
        super(message);
    }
    public InvalidAttributeChangeException(Throwable cause) {
        super(cause);
    }
    public InvalidAttributeChangeException(String message, Throwable cause) {
        super(cause);
    }

}
