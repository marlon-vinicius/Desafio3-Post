package com.example.challenge3.client;

import com.example.challenge3.dto.PostDTO;
import com.example.challenge3.entity.Comment;
import com.example.challenge3.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "PostClient", url = "https://jsonplaceholder.typicode.com")
public interface PostClient {

    @GetMapping("/posts")
    List<Post> getPosts();

    @GetMapping("/posts/{postId}/comments")
    List<Comment> getComments(@PathVariable("postId") Long postId);
}
