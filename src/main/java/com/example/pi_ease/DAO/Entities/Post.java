package com.example.pi_ease.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idP ;
    String Description ;
    String attachement ;
    int likes;
    @Temporal(TemporalType.DATE)
    Date DateP;
    @OneToMany
    List<Reaction> reactions ;
    @OneToMany (mappedBy = "post")
    List<Comment> comments;
    @ManyToOne
    User userpost ;
}
