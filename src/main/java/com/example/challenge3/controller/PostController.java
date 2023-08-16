package com.example.challenge3.controller;


import com.example.challenge3.dto.PostWithCommentsDTO;
import com.example.challenge3.service.ClientService;
import com.example.challenge3.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "post-api")
public class PostController {

    private final ClientService service;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostWithCommentsDTO>> queryPosts() {
        List<PostWithCommentsDTO> postsWithComments = service.getAllPostsWithComments();
        return ResponseEntity.ok(postsWithComments);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<String> processPost(@PathVariable Long postId) {
        postService.processPost(postId);
        return ResponseEntity.ok("Post processed.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> disablePost(@PathVariable Long postId) {
        postService.disablePost(postId);
        return ResponseEntity.ok("Post disabled.");
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> reprocessPost(@PathVariable Long postId) {
        postService.reprocessPost(postId);
        return ResponseEntity.ok("Post reprocessed.");
    }
}
