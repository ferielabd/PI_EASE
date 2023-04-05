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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idComm ;
    String Description ;
    @Temporal(TemporalType.DATE)
    Date DateCom;
    @OneToMany
    List<Reaction> reaction ;
    @ManyToOne
    Post post ;
    @ManyToOne
    Comment under_comment ;
    @OneToMany(mappedBy = "under_comment")
    List<Comment> ListCom ;
    @ManyToOne
    User userComment ;

}
