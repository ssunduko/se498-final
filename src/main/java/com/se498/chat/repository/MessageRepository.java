package com.se498.chat.repository;

import com.se498.chat.model.ChatMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<ChatMessage, String> {

    List<ChatMessage> findBySeed(int seed);
}