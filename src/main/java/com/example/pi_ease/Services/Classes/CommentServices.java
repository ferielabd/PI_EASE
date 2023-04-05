package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.BadgeType;
import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.CommentRepository;
import com.example.pi_ease.DAO.Repositories.PostRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Dto.CommentDto;
import com.example.pi_ease.Exceptions.PostNotFoundException;
import com.example.pi_ease.Mapper.CommentMapper;
import com.example.pi_ease.Services.Interface.ICommentServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentServices implements ICommentServices {
    private CommentRepository commentRepositoryy;
    private PostRepository postRepository;

    private UserRepository userRepository;
    private EmailServices emailServices ;
    private CommentMapper commentMapper;


    @Override
    public Comment add(Comment C) {
        return commentRepositoryy.save(C);
    }

    @Override
    public Comment edit(Comment C) {
        return commentRepositoryy.save(C);
    }

    @Override
    public List<Comment> selectAll() {
        return commentRepositoryy.findAll();
    }

    @Override
    public Comment selectById(long idComm) {
        return commentRepositoryy.findById(idComm).get();
    }

    @Override
    public void deleteByID(long idComm) {
        commentRepositoryy.deleteById(idComm);

    }



    @Override
    public List<Comment> addAll(List<Comment> list) {
        return list;
    }

    @Override
    public void deleteAll() {
        commentRepositoryy.deleteAll();


    }
    @Override
    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepositoryy.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    @Override
    public List<Comment> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepositoryy.findAllByUser(user);

    }
    @Override
    public String getForbiddenWords() {
        String content = "";
        try {

            File file = new File("C:\\Users\\ben slimen\\Desktop\\Pi\\forbiddenwords.txt");
            content = new String(Files.readAllBytes(file.toPath()));
            System.out.println(content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;

    }

    @Override
    public void setForbiddenWords(String words) {
        try {
            String filePath = "C:\\Users\\ben slimen\\Desktop\\Pi\\forbiddenwords.txt";
            FileOutputStream f = new FileOutputStream(filePath, true);
            String lineToAppend = getForbiddenWords() + "\t"
                    + words.replaceAll("[\\t\\n\\r]+", " ").replaceAll(" +", " ");
            byte[] byteArr = lineToAppend.getBytes();// converting string into byte array
            // pw.write(getForbiddenWords() + words.replaceAll("[\\t\\n\\r]+", "
            // ").replaceAll(" +", " "));
            f.write(byteArr);
            f.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void FilterCommentwithbadwords(long idComm, long id) {
        //User user = userRepository.findById(id).orElse(null);
        String[] forbiddenWords = getForbiddenWords().split(" ");
        boolean forbiden = false;
        Comment Comment = new Comment();
        Comment = commentRepositoryy.findById(idComm).get();
        String[] content = Comment.getText().split(" ");
        String newwcontent = "";
        for (String c : content) {
            System.out.println(c);
            User user = userRepository.findById(1L).get();
            System.out.println(user.getPhone());
            Comment.setUser(user);
            commentRepositoryy.save(Comment);
            for (String forbiddenWord : forbiddenWords) {
                if (c.contains(forbiddenWord)) {
                    this.emailServices.sendSimpleEmail(user.getEmail(), "badwords", "Attention!!!");

                    int alertCount = user.getAlertCount();
                    user.setAlertCount(alertCount + 1);
                    userRepository.save(user);
                    System.out.println(alertCount);
                    System.out.println("true");
                    System.out.println(forbiddenWord);
                    // c.replaceAll(c, "********");
                    //c.replace(forbiddenWord, "********");
                    Comment.setText("********");
                    System.out.println(newwcontent);
                    forbiden = true;
                    // newwcontent=+ String.join(" ", c);
                    // newwcontent.concat(String.join(" ", c));

                    if (alertCount >= 2) {
                        user.setActive(false);
                        userRepository.save(user);
                        this.emailServices.sendSimpleEmail(user.getMail(), "Account blocked", "your account is now blocked due to your bad behaviour ");
                    } else { // false
                        Comment.setText(String.join(" ", content));
                        Comment.setUser(user);
                        Comment.setDateCom(new Date());
                        List<Comment> usercomments = user.getCommentList();
                        usercomments.add(Comment);
                        //Post.setNlikes(0);
                        commentRepositoryy.save(Comment);
                    }
                }

            }
        }
    }

    @Override
    public void awardCommentBadges(User user) {
        int commentCount = commentRepositoryy.countByUser(user);
        if (commentCount > 10) {
            user.setCommentBadge(BadgeType.GOLD);
        } else if (commentCount > 5) {
            user.setCommentBadge(BadgeType.SILVER);
        } else if (commentCount > 0) {
            user.setCommentBadge(BadgeType.BRONZE);
        }
        userRepository.save(user);


    }

    @Override
    public Comment ajouterSousCommentaire(Long id, Long idComm, Comment underComment) {
        Comment parent = commentRepositoryy.findById(idComm).get();
        User user = userRepository.findById(id).get();
        underComment.setUnderComment(parent);
        underComment.setUserundercom(user);
        return commentRepositoryy.save(underComment);


    }

    @Override
    public List<Comment> afficherSousCommentaire(Long idComm) {
        Comment parent = commentRepositoryy.findById(idComm).get();
        List<Comment> listcom = commentRepositoryy.findByUnderComment(parent) ;
        return listcom ;


    }


}
