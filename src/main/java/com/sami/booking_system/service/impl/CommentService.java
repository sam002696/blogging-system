package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.CommentDTO;
import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.Comment;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.exception.OurException;
import com.sami.booking_system.repository.CommentRepository;
import com.sami.booking_system.repository.PostRepository;
import com.sami.booking_system.repository.UserRepository;
import com.sami.booking_system.service.interfaces.ICommentService;
import com.sami.booking_system.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService implements ICommentService {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;



    @Override
    public Response addNewComment(Long postId,Long userId, LocalDateTime createdAt, String content) {
        Response response = new Response();

        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new OurException("Post not found"));
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new OurException("User not found"));
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedAt(createdAt);
            comment.setPost(post);
            comment.setUser(user);

            Comment savedComment = commentRepository.save(comment);
            CommentDTO commentDTO = Utils.mapCommentEntityToCommentDTO(savedComment, false, true);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setComment(commentDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a comment " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllComments() {
        Response response = new Response();

        try {
            List<Comment> commentList = commentRepository.findAll();
            List<CommentDTO> commentDTOList = Utils.mapCommentListEntityToCommentListDTO(commentList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCommentList(commentDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all posts " + e.getMessage());
        }
        return response;

    }

    @Override
    public Response deleteComment(Long CommentId) {
        Response response = new Response();
        try {
            commentRepository.findById(CommentId).orElseThrow(() -> new OurException("comment Not Found"));
            commentRepository.deleteById(CommentId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting a comment " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateComment(Long CommentId, String content) {
        Response response = new Response();

        try {

            Comment comment = commentRepository.findById(CommentId).orElseThrow(() -> new OurException("Room Not Found"));

            if (content != null) comment.setContent(content);


            Comment updatedComment = commentRepository.save(comment);
            CommentDTO commentDTO = Utils.mapCommentEntityToCommentDTO(updatedComment, false,false);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setComment(commentDTO);

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
    public Response getCommentById(Long CommentId) {
        Response response = new Response();

        try {
            Comment comment = commentRepository.findById(CommentId).orElseThrow(() -> new OurException("Comment Not " +
                    "Found"));
            CommentDTO commentDTO = Utils.mapCommentEntityToCommentDTOPlusPosts(comment,true);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setComment(commentDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting comment by id " + e.getMessage());
        }
        return response;
    }
}
