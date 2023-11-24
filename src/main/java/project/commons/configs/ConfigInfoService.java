package project.commons.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.entities.Configs;
import project.repositories.ConfigsRepository;

/**
 * 설정 정보를 보여주는 기능
 */
@Service
@RequiredArgsConstructor
public class ConfigInfoService {

    private final ConfigsRepository repository;
    
    public <T> T get(String code, Class<T> clazz) {
        return get(code, clazz, null);
    }
    
    public <T> T get(String code, TypeReference<T> typeReference) {
        return get(code, null, typeReference);
    }
    
    public <T> T get(String code, Class<T> clazz, TypeReference<T> typeReference) {

        // 설정 코드에 해당하는 Configs 객체를 레포지토리에서 조회
        Configs config = repository.findById(code).orElse(null);

        // 조회된 Configs 객체가 null이거나 값이 비어있는 경우 null을 반환
        if (config == null || !StringUtils.hasText(config.getValue())) {
            return null;
        }

        // Configs 객체에서 JSON 형식의 값을 가져옴
        String json = config.getValue();

        // ObjectMapper를 생성 후 JavaTimeModule을 등록
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        try {
            T data = clazz == null ? om.readValue(json, typeReference) : om.readValue(json, clazz);

            // 역직렬화된 데이터를 반환합니다.
            return data;

        } catch (JsonProcessingException e) {
            // 위 과정에서 예외가 발생한 경우 예외를 출력하고 null을 반환한다
            e.printStackTrace();
            return null;
        }
    }
}
