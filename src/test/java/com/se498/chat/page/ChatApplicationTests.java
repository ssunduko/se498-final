package com.se498.chat.page;

import com.se498.chat.TestChatApplication;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestChatApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatApplicationTests {

    public static WebDriver driver;
    @LocalServerPort
    public int port;
    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);
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
    public void testUserSignupLoginAndSubmitMessage() throws IOException, InterruptedException {
        String username = "username1";
        String password = "password1";
        String messageText = "Hello!";

        TakesScreenshot screenshot;
        File source;

        driver.get(baseURL + "/signup");
        screenshot = (TakesScreenshot)driver;
        source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./DTAScreenshots/Signup.png"));

        SignUpPage signupPage = new SignUpPage(driver);
        signupPage.signUp("Participant", "1", username, password);

        driver.get(baseURL + "/login");
        screenshot = (TakesScreenshot)driver;
        source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./DTAScreenshots/Login.png"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/chat");

        screenshot = (TakesScreenshot)driver;
        source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./DTAScreenshots/Chat.png"));

        ChatPage chatPage = new ChatPage(driver);
        chatPage.sendChatMessage(messageText);

        /*WebElement revealed = driver.findElement(By.id("img01"));

        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(2))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(ElementNotInteractableException.class);

        wait.until(
                d -> {
                    revealed.sendKeys("How I feel");
                    return true;
                });*/

        chatPage.logout();

        driver.quit();
    }

}
