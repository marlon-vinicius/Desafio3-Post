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
public class PostEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 800)
    private String title;

    @Column(name = "body", length = 800)
    private String body;
}
