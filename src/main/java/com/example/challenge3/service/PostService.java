package com.example.challenge3.service;

import com.example.challenge3.entity.PostEntity;
import com.example.challenge3.entity.PostHistoryEntry;
import com.example.challenge3.entity.states.PostState;
import com.example.challenge3.repository.PostHistoryRepository;
import com.example.challenge3.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostHistoryRepository postHistoryRepository;

    @Async
    public CompletableFuture<Void> processPost(Long postId) {
        try {

            updatePostState(postId, PostState.POST_FIND);

            Thread.sleep(1000);

            if (isPostFound(postId)) {
                updatePostState(postId, PostState.POST_OK);

                Thread.sleep(1000);

                updatePostState(postId, PostState.COMMENTS_FIND);

                Thread.sleep(1000);

                if (areCommentsFound(postId)) {
                    updatePostState(postId, PostState.COMMENTS_OK);

                    Thread.sleep(1000);

                    updatePostState(postId, PostState.ENABLED);
                } else {
                    updatePostState(postId, PostState.FAILED);
                }
            } else {

                updatePostState(postId, PostState.FAILED);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> disablePost(Long postId) {
        try {
            updatePostState(postId, PostState.DISABLED);

            Thread.sleep(1000);

            updatePostState(postId, PostState.UPDATING);

            Thread.sleep(1000);

            updatePostState(postId, PostState.POST_FIND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(null);
    }

    private void updatePostState(Long postId, PostState newState) {
        Optional<PostEntity> postOptional = postRepository.findById(Long.valueOf(String.valueOf(postId)));
        postOptional.ifPresent(post -> {
            post.setPostState(newState);
            postRepository.save(post);
        });
    }

    private boolean isPostFound(Long postId) {

        return true;
    }

    private boolean areCommentsFound(Long postId) {

        return true;
    }

    public void reprocessPost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        if (post.getPostState() == PostState.ENABLED || post.getPostState() == PostState.DISABLED) {
            updatePostStateAndTime(post, PostState.POST_FIND);
            savePostHistoryEntry(post.getId(), PostState.POST_FIND);
        } else {
            throw new IllegalArgumentException("Post can only be reprocessed in ENABLED or DISABLED state.");
        }
    }

    private void updatePostStateAndTime(PostEntity post, PostState newState) {
        post.setPostState(newState);
        post.setLastStateUpdate(LocalDateTime.now());
        postRepository.save(post);
    }

    private void savePostHistoryEntry(Long postId, PostState state) {
        PostHistoryEntry historyEntry = PostHistoryEntry.builder()
                .postId(postId)
                .state(state)
                .timestamp(LocalDateTime.now())
                .build();
        postHistoryRepository.save(historyEntry);
    }
}
