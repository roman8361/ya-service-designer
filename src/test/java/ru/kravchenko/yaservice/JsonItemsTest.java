package ru.kravchenko.yaservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import ru.kravchenko.yaservice.constant.Constant;
import ru.kravchenko.yaservice.entity.json.Item;
import ru.kravchenko.yaservice.entity.json.RootElement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Roman Kravchenko
 */
public class JsonItemsTest {

    @Test
    public void testParsingItemsFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Constant.PATH_TO_FILE_JSON);
        Item item = objectMapper.readValue(file, Item.class);
        System.out.println(item.getPersonalInfo().getSocialLinks().getVk());
    }

    @Test
    public void testParsingItemsFromFileBelgorodPageOne() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Constant.PATH_TO_FILE_JSON_BELGOROD);
        RootElement rootElement = objectMapper.readValue(file, RootElement.class);
        System.out.println(rootElement);
    }

    @Test
    public void testParsingFromFile() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(Constant.PATH_TO_FILE_JSON));
        JSONObject jo = (JSONObject) obj;
        JSONObject jo2 = (JSONObject) jo.get("f677e48e-4a9e-415a-8325-6fdd8b5f0bb4");

        System.out.println(jo);
        System.out.println(jo2);

        // getting address
        Map address = ((Map)jo2.get("personalInfo"));
        Iterator<Map.Entry> itr1 = address.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }

}
