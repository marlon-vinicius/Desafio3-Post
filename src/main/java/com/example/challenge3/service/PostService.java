package com.example.challenge3.service;

import com.example.challenge3.entity.PostEntity;
import com.example.challenge3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public PostEntity savePost(PostEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error to save post" + e);
        }
    }

    public List<PostEntity> getAllPosts() {
        try{
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error to get all posts" + e);
        }
    }

    public Boolean existsByTitle(String title) {
        try {
            return repository.existsByTitle(title);
        }catch (Exception e) {
            throw new RuntimeException(format("Error to get post by title",title) + e);
        }
    }
}