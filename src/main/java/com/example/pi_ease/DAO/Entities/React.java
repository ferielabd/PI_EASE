package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class React implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idReaction ;

    @ManyToOne
    User UserLike ;
    @ManyToOne
    Post PostLike ;

    private static long serialVersionUID = 1L;
    // Les attributs
    @Enumerated(EnumType.STRING)
    Reaction reaction ;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        React.serialVersionUID = serialVersionUID;
    }
    public int getIdReaction() {
        return idReaction ;
    }

    public void setIdReaction(int idReaction) {
        this.idReaction = idReaction ;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction ;}

    public Post getPostLike() {
        return PostLike;
    }

    public void setPostLike(Post postLike) {
        PostLike = postLike;
    }

    public User getUserLike() {
        return UserLike;
    }

    public void setUserLike(User userLike) {
        UserLike = userLike;
    }

}
