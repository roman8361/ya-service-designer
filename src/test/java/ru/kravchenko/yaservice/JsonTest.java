package ru.kravchenko.yaservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.kravchenko.yaservice.constant.Constant;
import ru.kravchenko.yaservice.entity.json.Hobby;
import ru.kravchenko.yaservice.entity.json.PhoneData;
import ru.kravchenko.yaservice.entity.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Roman Kravchenko
 */
public class JsonTest {

    @Test
    @SneakyThrows
    public void testToJson() {
        final User user = new User();
        user.setLogin("admin");
        user.setPassword("qwerty");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user); // красивое преобразование
//        String json = objectMapper.writeValueAsString(user); // преобразование в одну строку

        System.out.println(json);
        Assert.assertNotNull(json);
    }

    @Test
    @SneakyThrows
    public void testToString() {
        String json = "{\"id\":\"d6407d79-50a5-4f17-b052-a90b7393520c\",\"login\":\"ivan\",\"password\":\"bratan\"}";
        ObjectMapper objectMapper = new ObjectMapper();

        User user = objectMapper.readValue(json, User.class);
        System.out.println(user);
    }

    @Test
    @SneakyThrows
    public void testGetPhoneFromJsonString() {
        String json = "{\"data\":{\"phone\":\"+79872442016\"},\"status\":200,\"type\":\"ydo_backend_response\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        PhoneData phoneData = objectMapper.readValue(json, PhoneData.class);
        System.out.println(phoneData.getData().getPhone());
    }

    @Test
    @SneakyThrows
    public void testToJsonGraph() {
        final User user = new User();
        user.setLogin("admin");
        user.setPassword("qwerty");
        user.setTelephone(getTelephone());
        user.setHobbyUser(getHobby());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user); // красивое преобразование
//        String json = objectMapper.writeValueAsString(user); // преобразование в одну строку

        System.out.println(json);
        Assert.assertNotNull(json);
    }


    List<String> getTelephone() {
        return Arrays.asList("1111", "2222", "3333");
    }

    Map<String, Map<String, Hobby>> getHobby() {
        Map<String, Map<String, Hobby>> result = new HashMap<>();
        Map<String, Hobby> hobbyMap1 = new HashMap<>();
        hobbyMap1.put("11", getHobbyEntity1());
        Map<String, Hobby> hobbyMap2 = new HashMap<>();
        hobbyMap2.put("22", getHobbyEntity2());
        Map<String, Hobby> hobbyMap3 = new HashMap<>();
        hobbyMap3.put("33", getHobbyEntity3());

        result.put("1", hobbyMap1);
        result.put("2", hobbyMap2);
        result.put("3", hobbyMap3);

        return result;
    }

    private Hobby getHobbyEntity1() {
        Hobby hobby = new Hobby();
        Map<String, String> result = new HashMap<>();
        result.put("111", "Football");
        result.put("222", "Football");
        result.put("333", "Football");
        hobby.setHobby(result);
        return hobby;
    }

    private Hobby getHobbyEntity2() {
        Hobby hobby = new Hobby();
        Map<String, String> result = new HashMap<>();
        result.put("111", "Volleyball");
        result.put("222", "Volleyball");
        result.put("333", "Volleyball");
        hobby.setHobby(result);
        return hobby;
    }

    private Hobby getHobbyEntity3() {
        Hobby hobby = new Hobby();
        Map<String, String> result = new HashMap<>();
        result.put("111", "Basketball");
        result.put("222", "Basketball");
        result.put("333", "Basketball");
        hobby.setHobby(result);
        return hobby;
    }

}
