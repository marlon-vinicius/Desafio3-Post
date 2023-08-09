package com.example.challenge3.controller;

import com.example.challenge3.entity.Post;
import com.example.challenge3.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public CompletableFuture<List<Post>> fetchAndEnrichPosts() {
        return postService.fetchAndEnrichPosts();
    }
}
