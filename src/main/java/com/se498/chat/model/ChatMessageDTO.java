package com.se498.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessageDTO {
    private String username;
    private String messageText;
    private String messageType;
}
