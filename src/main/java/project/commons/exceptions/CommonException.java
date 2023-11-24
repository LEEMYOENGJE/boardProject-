package project.commons.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
public class CommonException extends RuntimeException {

    private HttpStatus status;

    public CommonException(String message) {
        // HTTP 상태 코드를 기본값으로 설정 후 RuntimeException의 생성자를 호출
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public CommonException(String message, HttpStatus status) {
        // 주어진 메시지와 HTTP 상태 코드를 설정 뒤 RuntimeException의 생성자를 호출
        super(message);
        this.status = status;
    }
}
