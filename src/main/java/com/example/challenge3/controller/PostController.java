package com.example.challenge3.controller;

import com.example.challenge3.dto.CommentDTO;
import com.example.challenge3.dto.PostDTO;
import com.example.challenge3.dto.PostWithCommentsDTO;
import com.example.challenge3.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "post-api")
public class PostController {

    private final ClientService service;

    @Operation(summary = "Get all posts", method = "GET")
    @GetMapping("post")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return ResponseEntity.ok(service.getAllPosts());
    }

    @Operation(summary = "Get all comments", method = "GET")
    @GetMapping("comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        return ResponseEntity.ok(service.getAllComments());
    }

    @GetMapping("posts")
    public ResponseEntity<List<PostWithCommentsDTO>> getAllPostsWithComments() {
        List<PostWithCommentsDTO> postsWithComments = service.getAllPostsWithComments();
        return ResponseEntity.ok(postsWithComments);
    }
}