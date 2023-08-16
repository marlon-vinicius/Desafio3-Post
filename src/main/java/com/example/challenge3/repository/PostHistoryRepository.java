package com.example.challenge3.repository;


import com.example.challenge3.entity.PostHistoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostHistoryRepository extends JpaRepository<PostHistoryEntry, Long> {

    List<PostHistoryEntry> findByPostId(Long postId);
}
