package com.example.challenge3.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryEntryDTO {

    private Long id;
    private LocalDateTime date;
    private String status;
}
