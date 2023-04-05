package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String username ;


    int phone;
    int alertCount ;
    private Instant bannedUntil;
    @Temporal(TemporalType.DATE)
    Date birthDate;

    String mail;

    String password;
    @Enumerated(EnumType.STRING)
    BadgeType commentBadge;
    @Enumerated(EnumType.STRING)
    BadgeType postBadge;
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    @ManyToMany
    private List<Role> roles;
    @ManyToMany
    List<Notification> notifications;

    @OneToMany(mappedBy = "notifications")
    List<User> emitter;

    @OneToMany(mappedBy = "userCredit")
    List<Credit> creditList;

    @OneToMany(mappedBy = "userClaim")
    List<Claim> claimList;

    @ManyToMany(mappedBy = "userAcc")
    List<Account> accountList;

    @OneToMany
    List<Transaction> transactionE;

    @OneToMany
    List<Transaction> transactionR;
    @ManyToMany(mappedBy = "listUsersI")
    List<Project> listProjectI;
    @OneToMany(mappedBy = "userClient")
    List<Project> projectListC;

    @OneToOne
    Portfolio portfolio;
    @ManyToOne
    Room room;
    @OneToMany
    List<Message> messageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "UserLike")
    @JsonIgnore
    private List<React> likes ;

    @OneToMany(mappedBy = "userP", cascade = CascadeType.ALL)
    List<Post> postList ;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> commentList;

    private boolean active ;

    public User() {
    }


    public User(Long id, String username, int phone, Date birthDate, String mail, String password) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.birthDate = birthDate;
        this.mail = mail;
        this.password = password;
    }

    public User(String username, String mail, Date birthDate, String password, int phone) {
        this.username = username;
        this.phone = phone;
        this.birthDate = birthDate;
        this.mail = mail;
        this.password = password;
    }

    public boolean isAdmin() {
        return getTypeUser().equals(TypeUser.Admin);
    }

    public boolean isCandidate() {
        return getTypeUser().equals(TypeUser.Candidate);
    }

    public boolean isRecruter(User user) {
        return getTypeUser().equals(TypeUser.Recruiter);
    }



    public List<React> getLikes() {
        return likes;
    }

    public void setLikes(List<React> likes) {
        this.likes = likes;
    }

    ;
    public List<Post> getPostList() {
        return postList ;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}