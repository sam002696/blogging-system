package com.sami.booking_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.entity.User;
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

}
