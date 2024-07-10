package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.PostRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Post;


public interface IPostService {
    Post addPost(Long userId, PostRequest postRequest);

    Response addNewPost(String title, String content);

    Response getAllPosts();

    Response deletePost(Long postId);

    Response updatePost(Long postId, String title, String content);

    Response getPostById(Long PostId);

}
