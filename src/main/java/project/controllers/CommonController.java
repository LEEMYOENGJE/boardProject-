package project.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import project.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// 넓은 범위의 예외를 처리하는 클래스
@Slf4j
@ControllerAdvice("project.controllers")
public class CommonController {


    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 기본적으로 500 Internal Server Error 상태 코드를 설정
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        // 만약 예외가 CommonException 타입이라면, 예외에 설정된 HTTP 상태 코드를 사용
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException)e;
            status = commonException.getStatus();
        }

        // 응답 객체에 상태 코드를 설정
        response.setStatus(status.value());

        // 뷰에 전달할 속성을 Map에 담는 기능
        Map<String, String> attrs = new HashMap<>();
        attrs.put("status", String.valueOf(status.value()));
        attrs.put("path", request.getRequestURI());
        attrs.put("method", request.getMethod());
        attrs.put("message", e.getMessage());
        attrs.put("timestamp", LocalDateTime.now().toString());

        // 모델에 속성을 추가
        model.addAllAttributes(attrs);

        // 예외의 스택 트레이스를 로깅함
        Writer writer = new StringWriter();
        PrintWriter pr = new PrintWriter(writer);
        e.printStackTrace(pr);
        String errorMessage = ((StringWriter)writer).toString();
        log.error(errorMessage);

        // 에러 페이지로 이동하는 뷰의 이름을 반환
        return "error/common";
    }
}
