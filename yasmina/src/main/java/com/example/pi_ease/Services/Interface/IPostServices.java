package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Dto.PostRequest;
import com.example.pi_ease.DAO.Dto.PostResponse;
import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.React;
import com.example.pi_ease.DAO.Entities.User;

import java.util.List;

public interface IPostServices {
    Post add(Post P);
    Post edit(Post P);
    List<Post> selectAll();
    Post selectById(long postId);
    void deleteByID(long postId);
    List<Post>addAll(List<Post> list);
    void deleteAll ();
    PostResponse getPost(Long postId);
    List<PostResponse> getAllPosts();
    List<Post> getPostsByUsername(String userName);
    List<Post> trierParPopularite(List<Post> posts);
    public void SharePost(long postId, long id);
    public List<Post> GetSharedPosts(long postId);
    public void createPostForbidden(long postId, long id);
    public void setForbiddenWords(String words);
    public String getForbiddenWords();
    void awardPostBadges(User user);
    public void archivePostAfter3Days(Post post);
    boolean deletePost(Long postId);
    void deleteOldDeletedPostsDaily();
    void deleteOldDeletedPosts();
    void archivePost(Long postId);
    public Post getPostById(Long postId);

}
