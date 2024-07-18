package com.sami.booking_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sami.booking_system.entity.Post;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private UserDTO author;
    private List<CommentDTO> comments;

    public Post to() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        if (author != null) {
            post.setAuthor(author.to());
        }
        if (comments != null) {
            post.setComments(comments.stream().map(CommentDTO::to).collect(Collectors.toList())); // Assuming CommentDTO has a to() method to convert to Comment entity
        }
        return post;
    }

    public void update(Post post) {
        post.setTitle(title);
        post.setContent(content);
        if (author != null) {
            post.setAuthor(author.to());
        }
        if (comments != null) {
            post.setComments(comments.stream().map(CommentDTO::to).collect(Collectors.toList())); // Assuming CommentDTO has a to() method to convert to Comment entity
        }
    }
}
