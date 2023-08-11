package com.example.challenge3.controller;

import com.example.challenge3.entity.Comment;
import com.example.challenge3.entity.Post;
import com.example.challenge3.entity.PostWithComments;
import com.example.challenge3.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;



    @GetMapping
    public List<Post> getAllPostsWithComments() {
        List<Post> posts = postService.getAllPosts();
        List<Comment> comments = postService.getAllComments();

        // Associar os comentários aos posts
        for (Post post : posts) {
            List<Comment> postComments = comments.stream()
                    .filter(comment -> comment.getPost().getId().equals(post.getId()))
                    .collect(Collectors.toList());
            post.setComments(postComments);
        }

        return posts;
    }






//    @Autowired
//    private PostService postCommentService;
//
//    @GetMapping("/posts")
//    public List<PostWithComments> getPostsWithComments() {
//        return postCommentService.getPostsWithComments();
//    }
//
//    @PostMapping("/posts")
//    public Post createPost(@RequestBody Post post) {
//        return postCommentService.savePost(post);
//    }
//
//    @PostMapping("/comments")
//    public Comment createComment(@RequestBody Comment comment) {
//        return postCommentService.saveComment(comment);
//    }



























//    private final PostService postService;
//    private final PostRepository postRepository;
//
//    @Autowired
//    public PostController(PostService postService, PostRepository postRepository) {
//        this.postService = postService;
//        this.postRepository = postRepository;
//    }
//
//    @GetMapping
//    public CompletableFuture<List<Post>> fetchAndEnrichPosts() {
//    return postService.fetchAndEnrichPosts();
//}
//
//    @GetMapping("/posts")
//    public List<PostResponse> getPosts() {
//        List<Post> posts = postRepository.findAll();
//        List<PostResponse> responses = new ArrayList<>();
//
//        for (Post post : posts) {
//            List<PostState> stateHistory = post.getStateHistory();
//            List<HistoryEntry> historyEntries = new ArrayList<>();
//
//            for (long i = 0; i < stateHistory.size(); i++) {
//                historyEntries.add(new HistoryEntry(i + 1, new Date(), stateHistory.get((int) i).toString()));
//            }
//
//            List<CommentResponse> commentResponses = new ArrayList<>();
//
//            if (post.getState() == PostState.COMMENTS_OK) {
//                for (Comment comment : post.getComments()) {
//                    commentResponses.add(new CommentResponse(comment.getId(), comment.getBody()));
//                }
//            }
//
//            responses.add(new PostResponse(
//                    post.getId(),
//                    post.getTitle(),
//                    post.getBody(),
//                    commentResponses,
//                    historyEntries
//            ));
//        }
//
//        return responses;
//    }
//
//    @PutMapping("/posts/{postId}/process")
//    public ResponseEntity<String> processPost(@PathVariable Long postId) {
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (optionalPost.isPresent()) {
//            Post post = optionalPost.get();
//            if (post.getState() == PostState.CREATED) {
//                post.setState(PostState.POST_OK);
//                postRepository.save(post);
//                return ResponseEntity.ok("Post processed successfully.");
//            } else {
//                return ResponseEntity.badRequest().body("Post is not in the correct state for processing.");
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/posts/{postId}/disable")
//    public ResponseEntity<String> disablePost(@PathVariable Long postId) {
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (optionalPost.isPresent()) {
//            Post post = optionalPost.get();
//            if (post.getState() == PostState.ENABLED) {
//                post.setState(PostState.DISABLED);
//                postRepository.save(post);
//                return ResponseEntity.ok("Post disabled successfully.");
//            } else {
//                return ResponseEntity.badRequest().body("Post is not in the correct state for disabling.");
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/posts/{postId}/reprocess")
//    public ResponseEntity<String> reprocessPost(@PathVariable Long postId) {
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (optionalPost.isPresent()) {
//            Post post = optionalPost.get();
//            if (post.getState() == PostState.ENABLED || post.getState() == PostState.DISABLED) {
//                post.setState(PostState.UPDATING);
//                postRepository.save(post);
//                return ResponseEntity.ok("Post reprocessing initiated.");
//            } else {
//                return ResponseEntity.badRequest().body("Post is not in the correct state for reprocessing.");
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
