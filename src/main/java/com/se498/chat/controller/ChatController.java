package com.se498.chat.controller;

import com.se498.chat.model.ChatMessageDTO;
import com.se498.chat.model.ChatMessage;
import com.se498.chat.model.Image;
import com.se498.chat.service.MessageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    MessageService messageService;


    @GetMapping
    public String getChatPage(ChatMessageDTO chatMessageDTO, Model model) {
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        model.addAttribute("chatImages", this.messageService.getChatImages());
        return "chat";
    }

    @GetMapping("/modal/{index}")
    public String showModal(@PathVariable int index, Model model) {
        Image image = this.messageService.getChatImages().get(index);
        model.addAttribute("image", image);
        return "modal";

    }
    @PostMapping
    public String postChatMessage(ChatMessageDTO chatMessageDTO, Model model, Principal principal) {

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ChatMessageDTO, ChatMessage> propertyMapper = modelMapper.createTypeMap(ChatMessageDTO.class, ChatMessage.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> {
                    if (chatMessageDTO.getMessageType().equals("Shout")){
                        return chatMessageDTO.getMessageText().toUpperCase();
                    } else if(chatMessageDTO.getMessageType().equals("Whisper")){
                        return chatMessageDTO.getMessageText().toLowerCase();
                    }
                    return chatMessageDTO.getMessageText();
                }, ChatMessage::setMessageText)
        );

        chatMessageDTO.setUsername(principal.getName());

        this.messageService.addMessage(modelMapper.map(chatMessageDTO, ChatMessage.class));
        chatMessageDTO.setMessageText("");
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "redirect:/chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes () {

        return new String[] { "Say", "Shout", "Whisper" };
    }

}
