package com.example.pi_ease.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Document(collection = "post_archive")
public class Archive {


    @Id
    private long id;

    private Long postId;

    private String title;

    private String content;

    private User user;

    private Instant createdDate;

    private Instant archivedDate;
}
