package com.sami.booking_system.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostRequest {


    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "content is required")
    private String content;

}
