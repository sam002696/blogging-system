package com.sami.booking_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {

        @NotBlank(message = "content is required")
        private String content;

}
