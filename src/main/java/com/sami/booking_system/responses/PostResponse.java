package com.sami.booking_system.responses;

import com.sami.booking_system.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String content;
    private UserResponse author;
    private List<CommentResponse> comments;

    public static PostResponse select(Post post) {
        if (post == null) {
            return null;
        }
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setAuthor(UserResponse.select(post.getAuthor()));
        if (post.getComments() != null) {
            response.setComments(post.getComments().stream().map(CommentResponse::select).collect(Collectors.toList()));
        } else {
            response.setComments(Collections.emptyList());
        }
        return response;
    }
}
