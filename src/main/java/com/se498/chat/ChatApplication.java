package com.se498.chat;

import com.se498.chat.model.ChatMessage;
import com.se498.chat.model.Image;
import com.se498.chat.repository.ImageRepository;
import com.se498.chat.repository.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


import java.util.UUID;

@SpringBootApplication
@EntityScan(basePackages = {"com.se498.chat.model"})
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MessageRepository service) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			ChatMessage dummy = service.save(new ChatMessage(UUID.randomUUID().toString(), "test", "testing", 111111));

			System.out.println("This is a return Relational " + dummy.getMessageText());
		};
	}
}
