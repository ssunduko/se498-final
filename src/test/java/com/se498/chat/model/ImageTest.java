package com.se498.chat.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageTest {

    @Test
    void testImageInstantiation() {

        Image dummyImage = new Image("1","http://yahoo.com", "this is a test");

        assertAll("Verify Account properties",
                () -> assertEquals("1", dummyImage.getImageId()),
                () -> assertEquals("http://yahoo.com", dummyImage.getUrl()),
                () -> assertEquals("this is a test", dummyImage.getDescription()));

    }
}
