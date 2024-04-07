package com.se498.chat.controller;

import com.se498.chat.TestChatApplication;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(classes = TestChatApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageRestControllerTest {

    @LocalServerPort
    private static Integer port;

    @BeforeAll
    static void init(){

        ClientAndServer.startClientAndServer(1090);

        new MockServerClient("localhost", 1090)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/image/1"),
                        Times.unlimited(),
                        TimeToLive.unlimited(),
                        0
                )
                .respond(
                        response()
                                .withBody("{\n \"imageId\" : \"1\", \"url\" : \"http://yahoo.com\", \"description\" : \"description\"\n}")
                );
    }
    @Test
    void testGetImageById() throws JSONException {

        String expectedJson = "{\"imageId\" : \"1\", \"url\" : \"http://yahoo.com\", \"description\" : \"description\"}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:" + 1090 + "/image/1")
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(),true);
    }
}
