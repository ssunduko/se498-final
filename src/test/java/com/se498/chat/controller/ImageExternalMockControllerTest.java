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
public class ImageExternalMockControllerTest {

    @Test
    void testGetImageById() throws JSONException {

        String expectedJson = "{\"imageId\" : \"1\", \"url\" : \"https://loremflickr.com/640/480\", \"description\" : \"description 1\"}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("https://66122b1595fdb62f24ee293b.mockapi.io/image/1")
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(),true);
    }
}
