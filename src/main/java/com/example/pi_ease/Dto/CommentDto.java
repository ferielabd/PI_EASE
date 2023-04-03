package com.example.pi_ease.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long idComm ;
    private long postId;
    private Instant createdDate;
    private String text ;
    private String userName;
}
