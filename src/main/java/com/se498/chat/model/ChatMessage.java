package com.se498.chat.model;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ChatMessage {

    //TODO: Implement Persistent Entity requirements
    @Id
    private String messageId;
    private String username;
    private String messageText;
    private int seed;
}
