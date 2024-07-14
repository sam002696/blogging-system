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
    private UserResponse user;


    public static CommentResponse select(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setContent(comment.getContent());
//        response.setCreatedAt(comment.getCreatedAt());
        response.setUser(UserResponse.select(comment.getUser()));
        return response;
    }
}
