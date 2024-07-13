package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.CommentRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Comment;


import java.time.LocalDateTime;
import java.util.Optional;

public interface ICommentService {

    Response getAllComments();

    Response deleteComment(Long CommentId);

    Optional<Comment> updateComment(Long CommentId, CommentRequest commentRequest);

    Response getCommentById(Long CommentId);

    Comment addNewComment(Long postId, Long userId, CommentRequest commentRequest);
}
