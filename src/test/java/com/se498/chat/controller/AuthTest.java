package com.se498.chat.controller;

import com.se498.chat.TestChatApplication;
import com.se498.chat.model.ChatMessage;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = {TestChatApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    void testAuth() {

        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .then()
                .statusCode(200);
    }

    @Test
    public void testRetrieveImage(){
        RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:" + port + "/image/1")
                .then()
                .statusCode(200)
                .extract();

    }
}