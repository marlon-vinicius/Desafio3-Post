package com.example.challenge3.repository;

import com.example.challenge3.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String>{
    Boolean existsByBody(String body);
}
