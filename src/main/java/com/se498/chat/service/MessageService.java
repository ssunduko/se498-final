package com.se498.chat.service;

import com.se498.chat.model.*;
import com.se498.chat.repository.FakeImageRepository;
import com.se498.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AIService aiService;

    @Autowired
    FakeImageRepository fakeImageRepository;


    private final int seed = (int) (Math.random() * Integer.MAX_VALUE);

    public ChatMessage addMessage(ChatMessage message) {

        //TODO: Save original message in the database
        //TODO: Call AI service to visualize original message
        //TODO: Save original message visualization in the database

        //TODO: Call AI service to get response to the message
        //TODO: Save response in the database
        //TODO: Call AI service to visualize response
        //TODO: Save visualized response in the database

        return null;
    }

    public List<ChatMessage> getChatMessages() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.messageRepository.findBySeed(seed).iterator(), Spliterator.ORDERED), false)
                       .collect(Collectors.toList());
    }

    public List<Image> getChatImages() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.fakeImageRepository.findAll().iterator(), Spliterator.ORDERED), false)
                .collect(Collectors.toList());
    }

    public ChatMessage getMessage(String id){
        //TODO: Implement retrieving message from database
        return null;
    }
}
