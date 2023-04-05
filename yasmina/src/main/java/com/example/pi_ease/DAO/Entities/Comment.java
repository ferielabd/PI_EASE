package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComm ;

    private String text ;
    private String textsouscomment ;

    @ManyToOne
    @JsonIgnore
    private Post post;
    private Instant createdDate;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    @JsonIgnore
    private User userundercom;
    @Temporal(TemporalType.DATE)
    Date DateCom;
    @OneToMany
    List<React> reacts ;
    @ManyToOne
    @JsonIgnore
    Comment underComment ;
    @OneToMany(mappedBy = "underComment")
    List<Comment> ListCom ;


    public void setComment(Comment comment) {
    }


}
