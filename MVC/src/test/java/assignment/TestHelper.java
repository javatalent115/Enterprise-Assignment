package assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.stream.Collectors;

public class TestHelper {
    public static String toJson(Map<String, String> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Map objectToMap(Object ob) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(ob, Map.class);
    }

    public static String mapToString(Map<String, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> "\"" + key + "\"" + ":" + "\"" + map.get(key) + "\"")
                .collect(Collectors.joining(",", "{", "}"));
        return mapAsString;
    }
}
