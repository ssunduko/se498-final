package com.se498.chat.service;

import com.se498.chat.model.ChatRequest;
import com.se498.chat.model.ChatResponse;
import com.se498.chat.model.SimpleMessage;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class AIService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${openai.chatgtp.model}")
    private String GPT_MODEL;

    @Value("#{new Double('${openai.chatgtp.temperature}')}")
    private Double DEFAULT_TEMPERATURE;

    @Value("#{new Integer('${openai.chatgtp.max_tokens}')}")
    private Integer DEFAULT_MAX_TOKENS;

    @Value("${openai.chatgtp.max-completions}")
    private int DEFAULT_MAX_COMPLETIONS;

    @Value("${openai.chatgtp.api.url}")
    private String DEFAULT_API_URL;

    @Value("${openai.chatgtp.api.key}")
    private String openaiApiKey;

    private final static String DEFAULT_CONTEXT = "Imagine you are a world-renowned psychotherapist who uses Cognitive Behavioral Therapy to treat patients of all types. You are well-versed in the most efficient methods for helping people overcome personal struggles using CBT. You are an educator, scientist, and all-around warm-hearted person who understands how to help people. You have a sense of humor, a sharp wit, clarity in communication, and the ability to tell engaging stories and anecdotes to communicate the point you're trying to make. You also ask questions when uncertain so that you can deliver the best possible advice. You also want to help people discover for themselves the issues they are having, and empower them with practical advice on how to improve their own well-being.";


    public String visualizeText(String description){

        ImageModel model = OpenAiImageModel.builder()
                .apiKey(openaiApiKey)
                .build();

        Response<Image> image = model.generate(description + ",ultra realistic");

        return image.content().url().toString();
    }

    public String askQuestion(int seed, String actor, String question, String context){

        ChatRequest request = new ChatRequest(
                GPT_MODEL,
                List.of(new SimpleMessage ("system", ((context == null) ? DEFAULT_CONTEXT : context)), new SimpleMessage(actor, question)),
                DEFAULT_MAX_COMPLETIONS,
                DEFAULT_TEMPERATURE,
                DEFAULT_MAX_TOKENS, seed);

        //TODO: Call ChatGPT Service
        ChatResponse response = null;

        assert response != null;

        return response.getChoices().get(0).getMessage().getContent();
    }
}
