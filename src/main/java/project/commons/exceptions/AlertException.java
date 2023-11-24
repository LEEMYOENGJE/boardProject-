package project.commons.exceptions;

import org.springframework.http.HttpStatus;
//경고 표시 기능
public class AlertException extends CommonException {
    public AlertException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}