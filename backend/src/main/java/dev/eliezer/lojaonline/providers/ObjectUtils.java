package dev.eliezer.lojaonline.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.Map;

public class ObjectUtils {

    //A ideia dessa função é ser algo parecido com Oject.assign(objetoAtual, atualizacao) do Javascript
    public static Object objectUpdate (Object actual, Object update) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> updatedValues = objectMapper.convertValue(update, Map.class);

        for (Map.Entry<String, Object> entry : updatedValues.entrySet()) {
            if (entry.getValue() != null) {
                try {
                    Field field = actual.getClass().getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    field.set(actual, entry.getValue());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return actual;
    }
}
