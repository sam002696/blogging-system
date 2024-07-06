package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.Response;


import java.time.LocalDateTime;

public interface ICommentService {
    Response addNewComment(Long postId, Long userId, LocalDateTime createdAt, String content);

    Response getAllComments();

    Response deleteComment(Long CommentId);

    Response updateComment(Long CommentId, String content);

    Response getCommentById(Long CommentId);
}
