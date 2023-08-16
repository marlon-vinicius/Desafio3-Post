package com.example.challenge3.entity;

import com.example.challenge3.entity.states.PostState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_history") // Nome da tabela para o histórico de postagens
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostHistoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private PostState state;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
