package project.commons.exceptions;

import project.commons.Utils;

// 요청이 잘못되었을 때 사용되는 예외 클래스다
public class BadRequestException extends AlertBackException {
    public BadRequestException(String message) {
        super(message);
    }


     // 기본 메시지를 사용하여 BadRequestException을 생성합니다.
     //Utils 클래스를 활용하여 사전에 정의된 기본 에러 메시지를 설정합니다.

    public BadRequestException() {
        super(Utils.getMessage("BadRequest", "error"));
    }
}