package com.example.challenge3.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("postId")
    private Long postId;

    @JsonProperty("body")
    private String body;
}
