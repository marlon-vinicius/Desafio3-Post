package com.example.challenge3.converter;

import com.example.challenge3.dto.CommentDTO;
import com.example.challenge3.entity.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentConverter {

    public CommentEntity toEntity(CommentDTO dto) {
        return CommentEntity.builder()
                .id(dto.getId())
                .postId(dto.getPostId())
                .body(dto.getBody())
                .build();
    }

    public CommentDTO toDTO(CommentEntity entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .postId(entity.getPostId())
                .body(entity.getBody())
                .build();
    }

    public List<CommentDTO> toListDTO(List<CommentEntity> entityList) {
        return entityList.stream().map(this::toDTO).toList();
    }
}
