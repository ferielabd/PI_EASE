package com.example.pi_ease.DAO.Entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idP;
    String attachment;
    String description;
    String nameP ;
    @ManyToMany
    List<User> listUsersI;
    @ManyToOne
    User userClient;
    @ManyToMany(mappedBy = "listProject")
    private List<Portfolio> listPortfolio;

    @ManyToOne
    Category category;
}