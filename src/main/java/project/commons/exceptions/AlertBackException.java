package project.commons.exceptions;

//사용자에게 경고 메시지를 표시 후 이전 페이지로 이동시키는 예외 기능
public class AlertBackException extends AlertException {

    public AlertBackException(String message) {
        super(message);
    }
}