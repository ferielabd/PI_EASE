package com.example.pi_ease.Mapper;

import com.example.pi_ease.Dto.PostRequest;
import com.example.pi_ease.Dto.PostResponse;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.React;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.CommentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    private CommentRepository commentRepository;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "text", source = "postRequest.text")
    @Mapping(target = "userP", source = "userP")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map (PostRequest postRequest, React react1, User userP);

    @Mapping(target = "id", source = "postId")
   @Mapping(target = "reactName", source = "react.empty")
    @Mapping(target = "userName", source = "userP.username")

    public abstract PostResponse mapToDto(Post post);

    /*public String getDuration(Post post) {
        PrettyTime prettyTime = new PrettyTime();
        return prettyTime.format(post.getCreatedDate());
    }*/
}
