package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.PostRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.exception.OurException;
import com.sami.booking_system.exceptions.CustomMessageException;
import com.sami.booking_system.repository.CommentRepository;
import com.sami.booking_system.repository.PostRepository;
import com.sami.booking_system.repository.UserRepository;
import com.sami.booking_system.service.interfaces.IPostService;
import com.sami.booking_system.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;




    @Override
    public Response addNewPost(String content, String title) {
        Response response = new Response();

        try {
            Post post = new Post();
            post.setContent(content);
            post.setTitle(title);
            Post savedPost = postRepository.save(post);
            PostDTO postDTO = Utils.mapPostEntityToPostDTO(savedPost);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setPost(postDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a post " + e.getMessage());
        }
        return response;
    }


    @Override
    public Post addPost( PostRequest postRequest) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
//        post.setAuthor(user);

        return postRepository.save(post);
    }

//    @Override
//    public Post addNewPost(PostDTO dto) {
//        Post post = dto.to();
//        return postRepository.save(post);
//    }

    @Override
    public Response getAllPosts() {
        Response response = new Response();

        try {
            List<Post> postList = postRepository.findAll();
            List<PostDTO> postDTOList = Utils.mapPostListEntityToPostListDTO(postList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setPostList(postDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all posts " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deletePost(Long postId) {
        Response response = new Response();

        try {
            postRepository.findById(postId).orElseThrow(() -> new OurException("Post Not Found"));
            postRepository.deleteById(postId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Optional<Post> updatePost(Long postId, PostRequest postRequest) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomMessageException("Post Not Found"));
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return Optional.of(postRepository.save(post));
    }

//    @Override
//    public Response updatePost(Long postId, String title, String content) {
//        Response response = new Response();
//
//        try {
//
//            Post post = postRepository.findById(postId).orElseThrow(() -> new OurException("Room Not Found"));
//            if (title != null) post.setTitle(title);
//            if (content != null) post.setContent(content);
//
//
//            Post updatedPost = postRepository.save(post);
//            PostDTO postDTO = Utils.mapPostEntityToPostDTO(updatedPost);
//
//            response.setStatusCode(200);
//            response.setMessage("successful");
//            response.setPost(postDTO);
//
//        } catch (OurException e) {
//            response.setStatusCode(404);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error saving a room " + e.getMessage());
//        }
//        return response;
//    }

//    @Override
//    public Response getPostById(Long postId) {
//        Response response = new Response();
//
//        try {
//            Post post = postRepository.findById(postId).orElseThrow(() -> new OurException("Post Not Found"));
//            PostDTO postDTO = Utils.mapPostEntityToPostDTOPlusComments(post);
//            response.setStatusCode(200);
//            response.setMessage("successful");
//            response.setPost(postDTO);
//
//        } catch (OurException e) {
//            response.setStatusCode(404);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error saving a room " + e.getMessage());
//        }
//        return response;
//    }

    @Override
    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }
}
