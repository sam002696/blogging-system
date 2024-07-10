package com.sami.booking_system.controller;


import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.PostRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.service.interfaces.ICommentService;
import com.sami.booking_system.service.interfaces.IPostService;
import com.sami.booking_system.validators.PostValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import static com.sami.booking_system.exceptions.ApiError.fieldError;
import static com.sami.booking_system.responses.PostResponse.select;
import static com.sami.booking_system.utils.ResponseBuilder.error;
import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@Tag(name = "Posts API")
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private IPostService postService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private PostValidator postValidator;


    @PostMapping("/add/{userId}")
    @Operation(summary = "Add a post", responses = {
            @ApiResponse(description = "Successful added a post",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostRequest.class)))
    })
    public ResponseEntity<JSONObject> addNewPost(
            @PathVariable Long userId,
            @Valid @RequestBody PostRequest postRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Post post = postService.addPost(userId, postRequest);

        return ok(success(post, "Post added successfully").getJson());
    }
//    public ResponseEntity<Response> addNewRoom(
//            @RequestParam(value = "content", required = false) String content,
//            @RequestParam(value = "title", required = false) String title
//    ) {
//        if (content == null ||  title == null) {
//            Response response = new Response();
//            response.setStatusCode(400);
//            response.setMessage("Please provide values for all fields(content, title)");
//            return ResponseEntity.status(response.getStatusCode()).body(response);
//        }
//        Response response = postService.addNewPost(title, content);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllPosts() {
        Response response = postService.getAllPosts();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }



    @GetMapping("{postId}")
    public ResponseEntity<Response> getPostById(@PathVariable Long postId) {
        Response response = postService.getPostById(postId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }



    @PutMapping("/update/{postId}")
    public ResponseEntity<Response> updatePost(@PathVariable Long postId,
                                               @RequestParam(value = "title", required = false) String title,
                                               @RequestParam(value = "content", required = false) String content

    ) {
        Response response = postService.updatePost(postId, title, content);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{roomId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoom(@PathVariable Long postId) {
        Response response = postService.deletePost(postId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

}
