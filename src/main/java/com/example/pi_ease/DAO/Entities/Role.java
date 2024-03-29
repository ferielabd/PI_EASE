package com.example.pi_ease.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TypeRole name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TypeRole getName() {
        return name;
    }

    public void setName(TypeRole name) {
        this.name = name;
    }
}
