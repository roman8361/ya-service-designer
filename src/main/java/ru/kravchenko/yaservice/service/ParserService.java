package ru.kravchenko.yaservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kravchenko.yaservice.api.IParserService;
import ru.kravchenko.yaservice.constant.Constant;
import ru.kravchenko.yaservice.entity.Designer;
import ru.kravchenko.yaservice.entity.json.Item;
import ru.kravchenko.yaservice.entity.json.PhoneData;
import ru.kravchenko.yaservice.entity.json.RootElement;
import ru.kravchenko.yaservice.repository.DesignerRepository;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Roman Kravchenko
 */
@Service
public class ParserService implements IParserService {

    private final OkHttpClient httpClient = new OkHttpClient();

    @Autowired
    private DesignerRepository designerRepository;

    @SneakyThrows
    public void fillEntityDesigner(String pathToFileJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(pathToFileJson);
        RootElement rootElement = objectMapper.readValue(file, RootElement.class);
        Map<String, Item> items = rootElement.getWorkers().getItems();
        items.forEach((k, v) -> {
            Designer designer = new Designer();
            designer.setId(k);
            String phone = getPhone(k);
            designer.setPhone(phone);
            designer.setFirstName(v.getPersonalInfo().getFirstName());
            designer.setLastName(v.getPersonalInfo().getLastName());
            designer.setRating(v.getRating().toString());
            designer.setDescription(v.getPersonalInfo().getDescription());
            designer.setCompanyName(v.getPersonalInfo().getCompanyName());
          //  designer.setCity(v.getPersonalInfo().getAddressesList().getCityName());
            designer.setFacebook(v.getPersonalInfo().getSocialLinks().getFacebook());
            designer.setVk(v.getPersonalInfo().getSocialLinks().getVk());
            designer.setInstagram(v.getPersonalInfo().getSocialLinks().getInstagram());
           designerRepository.insert(designer);
        });
       for (Designer designer: designerRepository.getAll() ) {
           System.out.println(designer);
           System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
       }
    }

    private String getPhone(String id) {
//        String json = "{\"data\":{\"params\":{\"id\":\"8e7ece25-0821-43a1-b60f-766c7705591f\"}}}";
        String json = "{\"data\":{\"params\":{\"id\":\"" + id + "\"}}}";
        // json request body
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(Constant.URL_GET_TELEPHONE)
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get response body
            String responseString = response.body().string();
            System.out.println(responseString);

            ObjectMapper objectMapper = new ObjectMapper();
            PhoneData phoneData = objectMapper.readValue(responseString, PhoneData.class);
            System.out.println(phoneData.getData().getPhone());
            return phoneData.getData().getPhone();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
