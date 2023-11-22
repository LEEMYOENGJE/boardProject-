package project.commons.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.entities.Configs;
import project.repositories.ConfigsRepository;

@Service
@RequiredArgsConstructor
public class ConfigSaveService {

    private final ConfigsRepository repository;

    public <T> void save(String code, T t) {
        Configs configs = repository.findById(code).orElseGet(Configs::new);
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String json = null;
        try {
            json = om.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        configs.setCode(code);
        configs.setValue(json);

        repository.saveAndFlush(configs);
    }
}