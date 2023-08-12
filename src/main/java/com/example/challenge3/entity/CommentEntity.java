package com.example.challenge3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
