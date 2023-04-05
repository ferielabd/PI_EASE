package com.example.pi_ease.DAO.Mapper;

import com.example.pi_ease.DAO.Dto.CommentDto;
import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "idComm", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    public abstract Comment map(CommentDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    public abstract CommentDto mapToDto(Comment comment);
}
