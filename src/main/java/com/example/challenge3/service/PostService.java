package com.example.challenge3.service;

import com.example.challenge3.client.PostClient;
import com.example.challenge3.entity.Comment;
import com.example.challenge3.entity.Post;
import com.example.challenge3.repository.CommentRepository;
import com.example.challenge3.repository.PostRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostClient postClient ;

    public PostService(PostRepository postRepository, CommentRepository commentRepository, PostClient postClient) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.postClient = postClient;
    }


    @Async
    public CompletableFuture<List<Post>> fetchAndEnrichPosts() {

        List<Post> posts = postClient.getPosts();

        posts.forEach(post -> {
            List<Comment> comments = postClient.getComments(post.getId());
            post.setComments(comments);
        });

        posts = postRepository.saveAll(posts);

        return CompletableFuture.completedFuture(posts);
    }
}