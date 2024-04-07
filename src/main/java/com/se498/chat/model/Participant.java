package com.se498.chat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Participant {
    @Id
    private String userId;
    @Column
    private String username;
    @Column
    private String salt;
    @Column(length = 2048)
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
}