package com.example.pi_ease.DAO.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    private String ReactName ;
    private String postName;
    private String url;
    private String text;
}
