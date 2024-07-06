package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.Response;


public interface IPostService {

    Response addNewPost(String title, String content);

    Response getAllPosts();

    Response deletePost(Long postId);

    Response updatePost(Long postId, String title, String content);

    Response getPostById(Long PostId);

}
