package com.se498.chat.page;
import com.se498.chat.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {


    @FindBy(id = "messageText")
    private WebElement messageField;

    @FindBy(className = "chatMessageUsername")
    private WebElement chatMessageUsername;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "logout-link")
    private WebElement logoutLink;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sendChatMessage(String message) {
        messageField.clear();
        messageField.sendKeys(message);
        submitButton.click();
    }

    public void logout() {
        logoutLink.click();
    }

}
