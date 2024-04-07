package com.se498.chat.service;

import com.se498.chat.TestChatApplication;
import com.se498.chat.model.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestChatApplication.class})
public class ImageServiceIntegrationTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void testImageService() {

        Image dummyImage = new Image("1","http://yahoo.com", "this is a test");

        imageService.addImage(dummyImage);
        assertEquals(dummyImage, imageService.getImage("1"));
    }
}
