package com.example.challenge3.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "postId")
    private Long postId;

    @Column(name = "body", length = 800)
    private String body;
}
