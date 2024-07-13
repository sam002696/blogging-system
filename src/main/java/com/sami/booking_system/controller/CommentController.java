package com.sami.booking_system.controller;


import com.sami.booking_system.dto.*;
import com.sami.booking_system.entity.Comment;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.service.interfaces.ICommentService;
import com.sami.booking_system.service.interfaces.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.sami.booking_system.exceptions.ApiError.fieldError;
import static com.sami.booking_system.responses.CommentResponse.select;
import static com.sami.booking_system.utils.ResponseBuilder.error;
import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Comments API")
@RequestMapping("/api/v1/comment")
public class CommentController {


    @Autowired
    private ICommentService CommentService;


    @PostMapping("/add/{postId}/{userId}")
    @Operation(summary = "Add a comment to a post", responses = {
            @ApiResponse(description = "Successfully added the comment",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentRequest.class)))
    })
    public ResponseEntity<JSONObject> addNewComment(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest commentRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult), "Validation Error").getJson());
        }

         CommentService.addNewComment(postId, userId, commentRequest);
        return ok(success(null, "Comment added successfully").getJson());
    }

//    @GetMapping("/all")
//    public ResponseEntity<Response> getAllComments() {
//        Response response = CommentService.getAllComments();
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }



//    @GetMapping("/comment-by-id/{commentId}")
//    public ResponseEntity<Response> getCommentById(@PathVariable Long commentId) {
//        Response response = CommentService.getCommentById(commentId);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }



    @PutMapping("/update/{commentId}")
    @Operation(summary = "Update a comment", responses = {
            @ApiResponse(description = "Successfully updated the post",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentRequest.class)))
    })
    public ResponseEntity<JSONObject> updateComment(@PathVariable Long commentId,
                                               @RequestBody CommentRequest commentRequest
    ) {
//        Comment comment = CommentService.updateComment(commentId, commentRequest);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
        Optional<Comment> commentOptional = CommentService.updateComment(commentId, commentRequest);
        return commentOptional.map(value -> ok(success(select(value), "Comment Updated successfully").getJson())).orElseGet(() ->
                badRequest().body(error(HttpStatus.NOT_FOUND, "Comment not found with id: " + commentId).getJson()));
    }

    @DeleteMapping("/delete/{CommentId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteComment(@PathVariable Long CommentId) {
        Response response = CommentService.deleteComment(CommentId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
    
    
}
