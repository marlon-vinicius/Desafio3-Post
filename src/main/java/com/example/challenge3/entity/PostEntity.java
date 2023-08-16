package com.example.challenge3.entity;

import com.example.challenge3.entity.states.PostState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 800)
    private String title;

    @Column(name = "body", length = 800)
    private String body;

    @Column(name = "postState")
    private PostState postState;

    @Column(name = "last_state_update")
    private LocalDateTime lastStateUpdate;
}
