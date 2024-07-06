package com.sami.booking_system.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;

    private UserDTO user;
    private PostDTO post;
    private CommentDTO comment;
    private List<UserDTO> userList;
    private List<PostDTO> postList;
    private List<CommentDTO> commentList;


}
