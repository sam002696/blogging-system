package com.sami.booking_system.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sami.booking_system.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private UserDTO author;
    private List<CommentDTO> comments = new ArrayList<>();


}
