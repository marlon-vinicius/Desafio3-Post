package com.example.challenge3.client;

import com.example.challenge3.dto.CommentDTO;
import com.example.challenge3.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "PostClient", url = "https://jsonplaceholder.typicode.com")
public interface PostClient {
    @GetMapping("/posts")
    List<PostDTO> getPosts();

    @GetMapping("/comments")
    List<CommentDTO> getComments();
}
