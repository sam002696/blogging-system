package com.sami.booking_system.controller;


import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.PostRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.helpers.CommonDataHelper;
import com.sami.booking_system.responses.PostResponse;
import com.sami.booking_system.service.interfaces.ICommentService;
import com.sami.booking_system.service.interfaces.IPostService;
import com.sami.booking_system.utils.PaginatedResponse;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.sami.booking_system.exceptions.ApiError.fieldError;
import static com.sami.booking_system.responses.PostResponse.select;
import static com.sami.booking_system.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@Tag(name = "Posts API")
@RequestMapping("/api/v1/post")
public class PostController {


    @Autowired
    private IPostService postService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private PostValidator postValidator;
    @Autowired
    private CommonDataHelper commonDataHelper;


    @PostMapping("/add/{userId}")
    @Operation(summary = "Add a post", responses = {
            @ApiResponse(description = "Successfully added a post",
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
            return badRequest().body(error(fieldError(bindingResult), "Validation Error").getJson());
        }

        Post post = postService.addPost(postRequest, userId);

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

//    @GetMapping("/all")
//    public ResponseEntity<Response> getAllPosts() {
//        Response response = postService.getAllPosts();
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }

    @GetMapping("/list")
    @Operation(summary = "show lists of all posts", description = "show lists of all post")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))
    })
    public ResponseEntity<JSONObject> lists(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                                            @RequestParam(value = "search", defaultValue = "") String search
    ) {

        PaginatedResponse response = new PaginatedResponse();
        Map<String, Object> map = postService.search(page, size, sortBy, search);
        List<Post> postList = (List<Post>) map.get("lists");
        List<PostResponse> responses = postList.stream().map(PostResponse::select).toList();
        commonDataHelper.getCommonData(page, size, map, response, responses);
        return ok(paginatedSuccess(response).getJson());
    }



    @GetMapping("{postId}")
    @Operation(summary = "Get a post", description = "Get a post by it's id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))
    })
    public ResponseEntity<JSONObject> getPostById(@PathVariable Long postId) {
//        Optional<Post> postOptional = postService.findById(postId);

        PostResponse postResponse = postService.getPostById(postId);

        return ok(success(postResponse).getJson());


//        return postOptional.map(value -> ok(success(select(value)).getJson())).orElseGet(() ->
//                badRequest().body(error(HttpStatus.NOT_FOUND, "Post not found with id: " + postId).getJson()));

    }



    @PutMapping("/update/{postId}")
    @Operation(summary = "Update a post", responses = {
            @ApiResponse(description = "Successfully updated the post",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostRequest.class)))
    })
    public ResponseEntity<JSONObject> updatePost(@PathVariable Long postId,@RequestBody PostRequest postRequest
    ) {
        Optional<Post> postOptional = postService.updatePost(postId, postRequest);
        return postOptional.map(value -> ok(success(select(value)).getJson())).orElseGet(() ->
                badRequest().body(error(HttpStatus.NOT_FOUND, "Post not found with id: " + postId).getJson()));
    }

    @DeleteMapping("/delete/{postId}")
    @Operation(summary = "Delete a post", responses = {
            @ApiResponse(description = "Successfully deleted the post",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"
                            ))
    })
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoom(@PathVariable Long postId) {
        Response response = postService.deletePost(postId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

}
