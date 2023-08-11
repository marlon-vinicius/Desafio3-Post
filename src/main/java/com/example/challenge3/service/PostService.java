package com.example.challenge3.service;

import com.example.challenge3.client.JsonPlaceholderClient;
import com.example.challenge3.entity.Comment;
import com.example.challenge3.entity.Post;
import com.example.challenge3.entity.PostWithComments;
import com.example.challenge3.repository.CommentRepository;
import com.example.challenge3.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    private JsonPlaceholderClient postClient;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Post> getAllPosts() {
        return postClient.getPosts();
    }

    public void savePosts(List<Post> posts) {
        postRepository.saveAll(posts);
    }

    public List<Comment> getAllComments() {
        return postClient.getComments();
    }

    public void saveComments(List<Comment> comments) {
        commentRepository.saveAll(comments);
    }














//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    public List<PostWithComments> getPostsWithComments() {
//        List<Post> posts = postRepository.findAll();
//        Map<Long, List<Comment>> commentsMap = new HashMap<>();
//
//        for (Post post : posts) {
//            List<Comment> postComments = commentRepository.findByPostId(post.getId());
//            commentsMap.put(post.getId(), postComments);
//        }
//
//        List<PostWithComments> postsWithComments = new ArrayList<>();
//        for (Post post : posts) {
//            List<Comment> postComments = commentsMap.getOrDefault(post.getId(), new ArrayList<>());
//            postsWithComments.add(new PostWithComments(post, postComments));
//        }
//
//        return postsWithComments;
//    }
//
//    public Post savePost(Post post) {
//        return postRepository.save(post);
//    }
//
//    public Comment saveComment(Comment comment) {
//        return commentRepository.save(comment);
//    }





//    private final PostRepository postRepository;
//    private final CommentRepository commentRepository;
//    private final JsonPlaceholderClient jsonPlaceholderClient;
//
//    @Autowired
//    public PostService(PostRepository postRepository, CommentRepository commentRepository, JsonPlaceholderClient jsonPlaceholderClient) {
//        this.postRepository = postRepository;
//        this.commentRepository = commentRepository;
//        this.jsonPlaceholderClient = jsonPlaceholderClient;
//    }
//
//
//    @Async
//    public CompletableFuture<List<Post>> fetchAndEnrichPosts() {
//        List<Post> posts = jsonPlaceholderClient.getPosts();
//
//        for (Post post : posts) {
//            updatePostState(post, PostState.POST_FIND);
//            List<Comment> comments = jsonPlaceholderClient.getComments(post.getId());
//            post.setComments(comments);
//            updatePostState(post, PostState.COMMENTS_OK);
//        }
//
//        posts = postRepository.saveAll(posts);
//        return CompletableFuture.completedFuture(posts);
//    }
//
//    private void updatePostState(Post post, PostState newState) {
//        post.setState(newState);
//        post.getStateHistory().add(newState);
//        postRepository.save(post);
//    }


}