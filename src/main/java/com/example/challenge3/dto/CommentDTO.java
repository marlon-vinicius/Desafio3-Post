package com.example.challenge3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long postId;

    @JsonProperty("body")
    private String body;
}
