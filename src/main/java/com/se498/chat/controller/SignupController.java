package com.se498.chat.controller;

import com.se498.chat.service.ParticipantService;
import com.se498.chat.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    ParticipantService participantService;

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute Participant participant, Model model) {
        String signupError = null;

        if (!participantService.isUsernameAvailable(participant.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            if (participantService.createUser(participant) == null) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
