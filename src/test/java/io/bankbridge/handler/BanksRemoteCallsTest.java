package io.bankbridge.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.bankbridge.model.BankModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BanksRemoteCallsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() {
    }

    //test is written to show number of banks that should be picked up from the localhost
    @Test
    void handle() throws IOException, InterruptedException {
        HttpRequest request;

        try {
            //Create a new HTTP
            HttpClient client = HttpClient.newBuilder().build();
            //Call the API endpoint
            request = (HttpRequest) HttpRequest.newBuilder().uri(new URI("http://localhost:8080//v2/banks/all")).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            //Assert the expected output
            assertEquals(200, response.statusCode());

            //Retrieve from header the content type, but just type of file
            String[] contentType = response.headers().allValues("content-type").get(0).split(";");
            assertEquals("text/html", contentType[0]);

            //Assert array of banks size
            Gson gson = new Gson();
            List<BankModel> banks2 = gson.fromJson(response.body().toString(), new TypeToken<List<BankModel>>() {
            }.getType());
            assertEquals(20, banks2.size());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}