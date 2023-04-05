package com.example.pi_ease.DAO.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Id;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "post_archive")
public class PostArchive {
    @Id
    private long id;

    private Long postId;

    private String title;

    private String content;

    private User user;

    private Instant createdDate;

    private Instant archivedDate;
}
