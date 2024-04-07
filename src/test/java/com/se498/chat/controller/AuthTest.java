package com.se498.chat.controller;

import com.se498.chat.TestChatApplication;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = {TestChatApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthTest {

    @LocalServerPort
    private Integer port;

    @Test
    void testAuth() {

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