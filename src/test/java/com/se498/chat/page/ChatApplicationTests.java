package com.se498.chat.page;

import com.se498.chat.TestChatApplication;
import com.se498.chat.model.ChatMessage;
import com.se498.chat.page.ChatPage;
import com.se498.chat.page.LoginPage;
import com.se498.chat.page.SignUpPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestChatApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatApplicationTests {

    public static WebDriver driver;
    @LocalServerPort
    public int port;
    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new FirefoxDriver(options);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    public void testUserSignupLoginAndSubmitMessage() {
        String username = "username1";
        String password = "password1";
        String messageText = "Hello!";

        driver.get(baseURL + "/signup");

        SignUpPage signupPage = new SignUpPage(driver);
        signupPage.signUp("Participant", "1", username, password);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ChatPage chatPage = new ChatPage(driver);
        chatPage.sendChatMessage(messageText);

        chatPage.logout();

    }

}
