package com.sami.booking_system.controller;


import com.sami.booking_system.dto.Response;
import com.sami.booking_system.service.interfaces.ICommentService;
import com.sami.booking_system.service.interfaces.IPostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Posts API")
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private IPostService postService;
    @Autowired
    private ICommentService commentService;


    @PostMapping("/add")
    public ResponseEntity<Response> addNewRoom(
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "title", required = false) String title
    ) {
        if (content == null ||  title == null) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(content, title)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = postService.addNewPost(title, content);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

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
