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
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idC;

    String name;
    @ManyToOne
    Category cat;
    @OneToMany(mappedBy = "cat")
    List<Category> listCategory;
    @OneToMany(mappedBy = "category")
    List<Project> projectList;
}