package com.example.challenge3.repository;

import com.example.challenge3.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
    Boolean existsByTitle(String title);
}


