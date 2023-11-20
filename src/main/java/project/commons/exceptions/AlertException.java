package project.commons.exceptions;

import org.springframework.http.HttpStatus;

public class AlertException extends CommonsException {
    public AlertException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}