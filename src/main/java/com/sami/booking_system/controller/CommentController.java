package com.sami.booking_system.controller;


import com.sami.booking_system.dto.CommentDTO;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.dto.UserDTO;
import com.sami.booking_system.service.interfaces.ICommentService;
import com.sami.booking_system.service.interfaces.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Tag(name = "Comments API")
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    private ICommentService CommentService;


    @PostMapping("/add/{postId}/{userId}")
    @Operation(summary = "Add a comment to a post", responses = {
            @ApiResponse(description = "Successful added the comment",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDTO.class)))
    })
    public ResponseEntity<Response> addNewComment(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAt
    ) {
        if (content == null ||  createdAt == null) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(content, title)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = CommentService.addNewComment(postId, userId, createdAt, content);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllComments() {
        Response response = CommentService.getAllComments();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }



    @GetMapping("/comment-by-id/{commentId}")
    public ResponseEntity<Response> getCommentById(@PathVariable Long commentId) {
        Response response = CommentService.getCommentById(commentId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }



    @PutMapping("/update/{commentId}")
    public ResponseEntity<Response> updateComment(@PathVariable Long commentId,
                                               @RequestParam(value = "content", required = false) String content

    ) {
        Response response = CommentService.updateComment(commentId, content);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{CommentId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteComment(@PathVariable Long CommentId) {
        Response response = CommentService.deleteComment(CommentId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
    
    
}
