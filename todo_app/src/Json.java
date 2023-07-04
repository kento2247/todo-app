import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class json_object {
    String key;
    String val;
}

public class Json {
    public static void main(String[] args) {
        String str = "{\"a\":\"A\"},{\"b\":\"B\"}";
        System.out.println(str);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json_object node = objectMapper.readValue(str, json_object.class);
            System.out.println(node.key);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}