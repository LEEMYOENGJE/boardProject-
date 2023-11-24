package project.commons.rest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class JSONData<T> {

    // API 호출 성공 여부를 나타내는 필드이며 기본값은 true로 설정
    private boolean success = true;

    // API 응답의 HTTP 상태 코드를 나타내는 필드, 기본값은 OK (200)으로 설정
    private HttpStatus status = HttpStatus.OK;

    // 반드시 필요한 데이터를 담는 필드, NonNull 어노테이션을 사용하여 생성자에 의해 초기화 됨
    @NonNull
    private T data;

    // API 응답에 포함될 메시지를 나타내는 필드
    private String message;
}
