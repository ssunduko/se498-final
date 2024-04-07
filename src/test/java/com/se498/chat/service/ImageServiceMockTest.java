package com.se498.chat.service;

import com.se498.chat.TestChatApplication;
import com.se498.chat.model.Image;
import com.se498.chat.repository.FakeImageRepository;
import com.se498.chat.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {TestChatApplication.class})
public class ImageServiceMockTest {
    @Autowired
    private ImageService imageService;

    @Qualifier("imageRepository")
    @MockBean
    private ImageRepository imageRepository;

    @Test
    public void testGetAllImages() {
        Image dummyImage = new Image("1","http://yahoo.com", "this is a test");

        given(imageRepository.save(any(Image.class))).willReturn(dummyImage);
        imageService.addImage(dummyImage);
        verify(imageRepository, times(1)).save(dummyImage);
    }
}
