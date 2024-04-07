package com.se498.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String model;
    private List<SimpleMessage> messages;
    private int n;
    private double temperature;
    private int max_tokens;
    private int seed;
}
