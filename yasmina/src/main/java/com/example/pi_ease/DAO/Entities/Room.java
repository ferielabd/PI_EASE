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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idRoom;
    @Temporal(TemporalType.DATE)
    Date dateR ;
    @OneToMany(mappedBy = "roomM")
    List<Message> Listmsg;
    @OneToMany(mappedBy = "room")
    List<User>Listuser;

}
