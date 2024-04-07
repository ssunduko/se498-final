package com.se498.chat.repository;

import com.se498.chat.TestChatApplication;
import com.se498.chat.model.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestChatApplication.class})
public class ImageRepositoryMockTest {

    @Autowired
    @Qualifier("fakeImageRepository")
    private ImageRepository imageRepository;


    @Test
    public void testImageRepository(){

        Image dummyImage = new Image("1", "http://yahoo.com", "this is a test");
        imageRepository.save(dummyImage);
        assertEquals (dummyImage, imageRepository.findById("http://yahoo.com").get());
    }
}
