package project.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CommonsException extends RuntimeException {
    private  HttpStatus status;

    public CommonsException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

    public CommonsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    private HttpStatus getStatus() {
        return status;
    }
}
