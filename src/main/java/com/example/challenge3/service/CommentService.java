package com.example.challenge3.service;

import com.example.challenge3.entity.CommentEntity;
import com.example.challenge3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public CommentEntity saveComment(CommentEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error to save post" + e);
        }
    }

    public List<CommentEntity> getAllComments() {
        try{
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error to get all comments" + e);
        }
    }

    public Boolean existsByBody(String body) {
        try {
            return repository.existsByBody(body);
        }catch (Exception e) {
            throw new RuntimeException(format("Error to get post by body",body) + e);
        }
    }
}

