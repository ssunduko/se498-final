package com.se498.chat.controller;

import com.se498.chat.TestChatApplication;
import com.se498.chat.model.Image;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(classes = TestChatApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageInternalMockControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static HttpHeaders headers;

    @BeforeAll
    static void init(){

        headers = new HttpHeaders();
        headers.setBasicAuth("sergey", "chapman");
    }

    @Test
    public void testGetImageById() throws IllegalStateException, JSONException {

        String expectedJson = "{\"imageId\" : \"1\", \"url\" : \"http://yahoo.com\", \"description\" : \"description\"}";

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/image/1", HttpMethod.GET, new HttpEntity<String>(headers),
                String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }

    @Test
    public void testCreateImage() throws IllegalStateException, JSONException {

        String expectedJson = "{\"imageId\" : \"mock\", \"url\" : \"http://mock.com\", \"description\" : \"this is a test\"}";
        Image image = new Image("mock","http://mock.com", "this is a test");

        HttpEntity<Image> request = new HttpEntity<>(image, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/image", request, String.class);
        JSONAssert.assertEquals(expectedJson, response.getBody(),true);
    }
}
