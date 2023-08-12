package com.example.challenge3.service;

import com.example.challenge3.client.PostClient;
import com.example.challenge3.converter.CommentConverter;
import com.example.challenge3.converter.PostConverter;
import com.example.challenge3.dto.CommentDTO;
import com.example.challenge3.dto.PostDTO;
import com.example.challenge3.dto.PostWithCommentsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final PostClient client;
    private final PostConverter postConverter;
    private final PostService postService;
    private final CommentConverter commentConverter;
    private final CommentService commentService;

    public List<PostDTO> getAllPosts() {
        try {
            List<PostDTO> dto = client.getPosts();
            dto.forEach(post -> {
                        Boolean checkReturn = postService.existsByTitle(post.getTitle());
                        if (checkReturn.equals(false)) {
                            postService.savePost(postConverter.toEntity(post));
                        }
                    }
            );
            return postConverter.toListDTO((postService.getAllPosts()));
        } catch (Exception e) {
            throw new RuntimeException("Error to get and register post in database");
        }
    }

    public List<CommentDTO> getAllComments() {
        try {
            List<CommentDTO> dto = client.getComments();
            dto.forEach(comment -> {
                        Boolean checkReturn = commentService.existsByBody(comment.getBody());
                        if (checkReturn.equals(false)) {
                            commentService.saveComment(commentConverter.toEntity(comment));
                        }
                    }
            );
            return commentConverter.toListDTO((commentService.getAllComments()));
        } catch (Exception e) {
            throw new RuntimeException("Error to get and register post in database");
        }
    }
    public List<PostWithCommentsDTO> getAllPostsWithComments() {
        List<PostDTO> posts = client.getPosts();
        List<CommentDTO> comments = client.getComments();

        Map<Long, List<CommentDTO>> commentsByPostId = comments.stream()
                .collect(Collectors.groupingBy(CommentDTO::getPostId));

        List<PostWithCommentsDTO> postsWithComments = new ArrayList<>();
        for (PostDTO post : posts) {
            List<CommentDTO> postComments = commentsByPostId.getOrDefault(post.getId(), Collections.emptyList());
            postsWithComments.add(PostWithCommentsDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .body(post.getBody())
                    .comments(postComments)
                    .build());
        }
        return postsWithComments;
    }
}
