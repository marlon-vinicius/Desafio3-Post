package com.example.challenge3.service;

import com.example.challenge3.client.PostClient;
import com.example.challenge3.dto.CommentDTO;
import com.example.challenge3.dto.HistoryEntryDTO;
import com.example.challenge3.dto.PostDTO;
import com.example.challenge3.dto.PostWithCommentsDTO;
import com.example.challenge3.entity.CommentEntity;
import com.example.challenge3.entity.PostEntity;
import com.example.challenge3.entity.PostHistoryEntry;
import com.example.challenge3.entity.states.PostState;
import com.example.challenge3.repository.CommentRepository;
import com.example.challenge3.repository.PostHistoryRepository;
import com.example.challenge3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final PostClient client;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostHistoryRepository postHistoryRepository;

    public List<PostWithCommentsDTO> getAllPostsWithComments() {
        List<PostDTO> posts = client.getPosts();
        List<CommentDTO> comments = client.getComments();

        Map<Long, List<CommentDTO>> commentsByPostId = comments.stream()
                .collect(Collectors.groupingBy(CommentDTO::getPostId));

        List<PostWithCommentsDTO> postsWithComments = new ArrayList<>();
        for (PostDTO post : posts) {
            List<CommentDTO> postComments = commentsByPostId.getOrDefault(post.getId(), Collections.emptyList());

            PostEntity savedPost = savePost(post);

            List<CommentEntity> savedComments = saveComments(savedPost, postComments);

            savePostHistoryEntry(savedPost.getId(), PostState.ENABLED);

            postsWithComments.add(PostWithCommentsDTO.builder()
                    .id(savedPost.getId())
                    .title(savedPost.getTitle())
                    .body(savedPost.getBody())
                    .comments(savedComments.stream().map(this::mapCommentDTO).collect(Collectors.toList()))
                    .history(getHistoryEntries(savedPost.getId()))
                    .build());
        }
        return postsWithComments;
    }

    private PostEntity savePost(PostDTO postDTO) {
        PostEntity post = PostEntity.builder()
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .body(postDTO.getBody())
                .build();
        return postRepository.save(post);
    }

    private List<CommentEntity> saveComments(PostEntity postEntity, List<CommentDTO> commentDTOs) {
        List<CommentEntity> savedComments = new ArrayList<>();
        for (CommentDTO commentDTO : commentDTOs) {
            CommentEntity comment = CommentEntity.builder()
                    .id(commentDTO.getId())
                    .postId(postEntity.getId())
                    .body(commentDTO.getBody())
                    .build();
            savedComments.add(commentRepository.save(comment));
        }
        return savedComments;
    }

    private void savePostHistoryEntry(Long postId, PostState state) {
        PostHistoryEntry historyEntry = PostHistoryEntry.builder()
                .postId(postId)
                .state(state)
                .timestamp(LocalDateTime.now())
                .build();
        postHistoryRepository.save(historyEntry);
    }

    private List<HistoryEntryDTO> getHistoryEntries(Long postId) {
        List<PostHistoryEntry> historyEntries = postHistoryRepository.findByPostId(postId);
        return historyEntries.stream()
                .map(this::mapHistoryEntry)
                .collect(Collectors.toList());
    }

    private HistoryEntryDTO mapHistoryEntry(PostHistoryEntry historyEntry) {
        return HistoryEntryDTO.builder()
                .id(historyEntry.getId())
                .date(historyEntry.getTimestamp())
                .status(String.valueOf(PostState.valueOf(historyEntry.getState().toString())))
                .build();
    }

    private CommentDTO mapCommentDTO(CommentEntity commentEntity) {
        return CommentDTO.builder()
                .id(commentEntity.getId())
                .body(commentEntity.getBody())
                .build();
    }
}
