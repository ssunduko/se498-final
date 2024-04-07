package com.se498.chat;

import com.se498.chat.model.Image;
import com.se498.chat.repository.ImageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestChatApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestChatApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunnerToo(ImageRepository imageRepository) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            Image dummy = imageRepository.save(new Image("1", "http://yahoo.com", "description"));

            System.out.println("This is a return Relational " + dummy);
        };
    }
}
