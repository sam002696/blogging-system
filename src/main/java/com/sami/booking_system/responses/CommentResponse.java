package com.sami.booking_system.responses;

import com.sami.booking_system.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserResponse user;
    private PostResponse post;

    public static CommentResponse select(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUser(UserResponse.select(comment.getUser())); // Assuming UserResponse has a similar select method
        response.setPost(PostResponse.select(comment.getPost())); // Assuming PostResponse has a similar select method
        return response;
    }
}
