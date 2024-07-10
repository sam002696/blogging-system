package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.PostRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Post;

import java.util.Optional;


public interface IPostService {
    Post addPost( PostRequest postRequest);

    Response addNewPost(String title, String content);

    Response getAllPosts();

    Response deletePost(Long postId);

    Optional<Post> updatePost(Long postId, PostRequest postRequest);

    Optional<Post> findById(Long postId);

}
