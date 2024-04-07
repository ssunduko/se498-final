package com.se498.chat.service;

import com.se498.chat.model.Participant;
import com.se498.chat.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    HashService hashService;


    public boolean isUsernameAvailable(String username) {

        return this.participantRepository.findByUsername(username) == null;
    }

    public String createUser(Participant participant) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(participant.getPassword(), encodedSalt);

        return this.participantRepository.save(new Participant(UUID.randomUUID().toString(), participant.getUsername(), encodedSalt, hashedPassword, participant.getFirstName(), participant.getLastName())).getUserId();
    }

    public Participant getParticipant(String username) {
        return this.participantRepository.findByUsername(username);
    }
}
