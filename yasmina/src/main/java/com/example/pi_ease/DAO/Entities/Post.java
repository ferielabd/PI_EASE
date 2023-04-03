package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.geometry.Pos;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

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
    private long postId;
    private String attachement;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private String postName;


    @Lob
    private String text;
    private Integer voteCount = 0;
    @Nullable
    private String url;
    @ManyToOne
    @JsonIgnore
    private User userP ;
    private Instant createdDate;


    @OneToMany(cascade = CascadeType.ALL,mappedBy="PostLike")
    @JsonIgnore
    private List<React> react ;
    private int Nlikes;
    private boolean deleted ;
    @Temporal(TemporalType.DATE)
    private Date datep;
    @OneToMany(mappedBy = "post")
    private List<Post> children = new ArrayList<Post>();
    @ManyToOne
    @JsonIgnore
    private Post post ;

    @Nullable
    private Date deletedTime;
    public String toString() {
        return "Post [postId=" + postId + ", text=" + text + ", date=" + datep + ", Nlikes=" + Nlikes + "]";
    }



    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return datep;
    }

    public void setDate(Date date) {
        this.datep = date;
    }

    public int getNlikes() {
        return Nlikes;
    }

    public void setNlikes(int nlikes) {
        Nlikes = nlikes;
    }

    public User getUserP() {
        return userP;
    }

    public void setUserP(User userP) {
        this.userP = userP;
    }
    public List<React> getLikes() {
        return react ;
    }

    public Post getPost() {
        return post ;
    }

    public void setPost(Post post) {
        this.post = post ;
    }
    public List<Post> getChildren() {
        return children;
    }

    public void setChildren(List<Post> children) {
        this.children = children;
    }
}