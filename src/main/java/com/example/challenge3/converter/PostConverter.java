package com.example.challenge3.converter;

import com.example.challenge3.dto.PostDTO;
import com.example.challenge3.entity.PostEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostConverter {

    public PostEntity toEntity(PostDTO dto) {
        return PostEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }

    public PostDTO toDTO(PostEntity entity) {
        return PostDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .build();
    }

    public List<PostDTO> toListDTO(List<PostEntity> entityList) {
        return entityList.stream().map(this::toDTO).toList();
    }
}
