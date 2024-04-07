package com.se498.chat.repository;

import com.se498.chat.model.Participant;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, String>{

    Participant findByUsername(String username);
}
