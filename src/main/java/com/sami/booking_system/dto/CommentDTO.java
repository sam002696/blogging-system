package com.sami.booking_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sami.booking_system.entity.Comment;
import com.sami.booking_system.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserDTO user;
    private PostDTO post;

    public Comment to() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent(content);
//        comment.setCreatedAt(createdAt);
        if (user != null) {
            comment.setUser(user.to()); // Assuming UserDTO has a to() method to convert to User entity
        }
        if (post != null) {
            comment.setPost(post.to()); // Assuming PostDTO has a to() method to convert to Post entity
        }
        return comment;
    }
}
