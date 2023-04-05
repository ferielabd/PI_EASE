package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.*;
import com.example.pi_ease.DAO.Repositories.ArchiveRepository;
import com.example.pi_ease.Dto.PostResponse;
import com.example.pi_ease.Exceptions.PostNotFoundException;
import com.example.pi_ease.Mapper.PostMapper;
import com.example.pi_ease.DAO.Repositories.PostRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Services.Interface.IPostServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableScheduling
@Slf4j
@AllArgsConstructor
public class PostServices  implements IPostServices {
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private EmailServices emailServices;
    private ArchiveRepository archiveRepository;
    private PostMapper postMapper;

    @Override
    public Post add(Post P) {
        return postRepository.save(P);
    }

    @Override
    public Post edit(Post P) {

        return postRepository.save(P);
    }

    @Override
    public List<Post> selectAll() {
        return postRepository.findAll();
    }

    @Override
    public Post selectById(long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public void deleteByID(long postId) {
        postRepository.deleteById(postId);

    }

    @Override
    public List<Post> addAll(List<Post> list) {
        return list ;
    }


    @Override
    public void deleteAll() {
        postRepository.deleteAll();

    }

    @Override
    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return postMapper.mapToDto(post);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> trierParPopularite(List<Post> posts) {
        return posts.stream()
                .sorted(Comparator.comparingInt(Post::getNlikes).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostsByUsername(String userName) {

        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return postRepository.findByUserP(user);
    }

    public void archivePostAfter3Days(Post post) {
        long currentTimeMillis = System.currentTimeMillis();
        long createTimeMillis = post.getCreatedDate().toEpochMilli();
        long threeDaysInMillis = 3 * 24 * 60 * 60 * 1000;
        if (post.getNlikes() == 0 && (currentTimeMillis - createTimeMillis) > threeDaysInMillis) {
            archivePost(post.getPostId());
        }
    }

    @Scheduled(cron = "0 20 03 * * *") // every day at 00:00
    public void deleteOldDeletedPostsDaily() {
        List<Post> deletedPosts = postRepository.findAll();
        for (Post post : deletedPosts) {
            archivePostAfter3Days(post);
        }
        this.emailServices.sendSimpleEmail("mahnoud.mbshk@gmail.com", "badwords", "Attention!!!");

    }

    @Transactional(readOnly = true)
    public boolean deletePost(Long postId) {
        // Get the current user from the authentication service
        //User currentUser = authController.getCurrentUser();
        User currentUser = userRepository.findById(1L).get();
        // Get the post to delete from the post repository
        Post postToDelete = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));

        // Check if the user is authorized to delete the post
        if (postToDelete.getUserP().equals(currentUser)) {
            postToDelete.setDeleted(true);
            postRepository.save(postToDelete);
            return true;
        } else if (currentUser.isAdmin()) {
            archivePost(postRepository.findById(postId).get().getPostId());/*norml archiv???*/
        }

        // User is not authorized to delete the post
        return false;
    }

    /*@Override
    public void awardPostBadges(User user) {
        int postCount = postRepository.countByUserP(user);
        if (postCount > 10) {
            user.setPostBadge(BadgeType.GOLD);
        } else if (postCount > 5) {
            user.setPostBadge(BadgeType.SILVER);
        } else if (postCount > 0) {
            user.setPostBadge(BadgeType.BRONZE);
        }
        userRepository.save(user);
    }*/

    @Override
    public Post getPostById(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElse(null);
    }

    public void archivePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id - " + postId));
        User user = userRepository.findById(1L).get();
        System.out.println(user.getPhone());
        if (post.getUserP().getId() != (user.getId())) {
            throw new AccessDeniedException("You can only archive your own posts.");
        }

        Archive postArchive = new Archive();
        postArchive.setPostId(post.getPostId());
        postArchive.setTitle(post.getPostName());
        postArchive.setContent(post.getText());
        postArchive.setUser(post.getUserP());
        postArchive.setCreatedDate(post.getCreatedDate());
        postArchive.setArchivedDate(Instant.now());

        //archiveRepository.save(postArchive);
        postRepository.delete(post);
    }


    @Override
    public void SharePost(long postId, long id) {

        Post PostParent = postRepository.findById(postId).orElse(null);
        User userShare = userRepository.findById(id).orElse(null);
        Post r = new Post();
        r.setDate(new Date());
        r.setNlikes(0);
        r.setPost(PostParent);
        r.setText(PostParent.getText());
        r.setUserP(userShare);
        List<Post> shares = PostParent.getChildren();
        shares.add(r);
        postRepository.save(r);

    }

    @Override
    public List<Post> GetSharedPosts(long postId) {
        // TODO Auto-generated method stub
        Post PostParent = postRepository.findById(postId).orElse(null);
        List<Post> Shared = PostParent.getChildren();
        return Shared;

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
    public void FilterPostwithbadwords(long postId, long id) {


        //User user = userRepository.findById(id).orElse(null);

        String[] forbiddenWords = getForbiddenWords().split(" ");
        boolean forbiden = false;
        Post Post = new Post();
        Post = postRepository.findById(postId).get();
        String[] content = Post.getText().split(" ");
        String newwcontent = "";
        for (String c : content) {
            System.out.println(c);
            User user = userRepository.findById(1L).get();
            System.out.println(user.getPhone());
            Post.setUserP(user);
            postRepository.save(Post);
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
                    Post.setText("********");
                    System.out.println(newwcontent);
                    forbiden = true;
                    // newwcontent=+ String.join(" ", c);
                    // newwcontent.concat(String.join(" ", c));

                    if (alertCount >= 2) {
                        user.setActive(false);
                        userRepository.save(user);
                        this.emailServices.sendSimpleEmail(user.getMail(), "Account blocked", "your account is now blocked due to your bad behaviour ");
                    } else { // false
                        Post.setText(String.join(" ", content));
                        Post.setUserP(user);
                        Post.setDate(new Date());
                        List<Post> userposts = user.getPostList();
                        userposts.add(Post);
                        //Post.setNlikes(0);
                        postRepository.save(Post);
                    }
                }

            }
        }
    }
}

