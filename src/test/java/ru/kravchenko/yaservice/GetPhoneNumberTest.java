package ru.kravchenko.yaservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.Test;
import ru.kravchenko.yaservice.constant.Constant;
import ru.kravchenko.yaservice.entity.json.PhoneData;

import java.io.IOException;

/**
 * @author Roman Kravchenko
 */
public class GetPhoneNumberTest {

    private final OkHttpClient httpClient = new OkHttpClient();

  //  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String json = "{\"data\":{\"params\":{\"id\":\"8e7ece25-0821-43a1-b60f-766c7705591f\"}}}";

    @Test
    public void testTrueMethodJsonRequest() {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
