package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.exception.OurException;
import com.sami.booking_system.repository.CommentRepository;
import com.sami.booking_system.repository.PostRepository;
import com.sami.booking_system.service.interfaces.IPostService;
import com.sami.booking_system.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;



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
    public Response updatePost(Long postId, String title, String content) {
        Response response = new Response();

        try {

            Post post = postRepository.findById(postId).orElseThrow(() -> new OurException("Room Not Found"));
            if (title != null) post.setTitle(title);
            if (content != null) post.setContent(content);


            Post updatedPost = postRepository.save(post);
            PostDTO postDTO = Utils.mapPostEntityToPostDTO(updatedPost);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setPost(postDTO);

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
    public Response getPostById(Long postId) {
        Response response = new Response();

        try {
            Post post = postRepository.findById(postId).orElseThrow(() -> new OurException("Room Not Found"));
            PostDTO postDTO = Utils.mapPostEntityToPostDTOPlusComments(post);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setPost(postDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }
}
