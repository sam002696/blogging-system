package com.sami.booking_system.utils;


import com.sami.booking_system.dto.CommentDTO;
import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.dto.UserDTO;
import com.sami.booking_system.entity.Comment;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.entity.User;


import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static UserDTO mapUserEntityToUserDTO(User user, boolean includePost) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (includePost) {
            userDTO.setPosts(user.getPosts().stream()
                    .map(Utils::mapPostEntityToPostDTO)
                    .collect(Collectors.toList()));
        }
        else {
            userDTO.setPosts(null); // Explicitly set to null
        }
        return userDTO;
    }

    public static PostDTO mapPostEntityToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());


        return postDTO;
    }

    public static CommentDTO mapCommentEntityToCommentDTO(Comment comment, Boolean includePost, Boolean includeUser) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());

        if (includePost) {
            commentDTO.setPost(mapPostEntityToPostDTO(comment.getPost()));
        }

        if(includeUser){
            commentDTO.setUser(mapUserEntityToUserDTO(comment.getUser(), false));
        }

        return commentDTO;
    }

    public static PostDTO mapPostEntityToPostDTOPlusComments(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());

        if (post.getComments() != null) {
            postDTO.setComments(post.getComments().stream().map(comment -> mapCommentEntityToCommentDTO(comment,
                    false, true)).collect(Collectors.toList()));
        }
        return postDTO;
    }

    public static CommentDTO mapCommentEntityToCommentDTOPlusPosts(Comment comment, boolean mapUser) {

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());

        if (mapUser) {
            commentDTO.setUser(Utils.mapUserEntityToUserDTO(comment.getUser(), false));
        }
        if (comment.getPost() != null) {
            commentDTO.setPost(Utils.mapPostEntityToPostDTO(comment.getPost()));
        }
        return commentDTO;
    }

    public static UserDTO mapUserEntityToUserDTOPlusUserPostsAndComments(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getPosts().isEmpty()) {
            userDTO.setPosts(user.getPosts().stream().map(post -> mapPostEntityToPostDTOPlusComments(post)).collect(Collectors.toList()));
        }
        return userDTO;
    }


    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList) {
        return userList.stream().map( user ->  mapUserEntityToUserDTO(user, true)).collect(Collectors.toList());
    }

    public static List<PostDTO> mapPostListEntityToPostListDTO(List<Post> postList) {
        return postList.stream().map(Utils::mapPostEntityToPostDTO).collect(Collectors.toList());
    }

    public static List<CommentDTO> mapCommentListEntityToCommentListDTO(List<Comment> commentList) {
        return commentList.stream().map(comment -> mapCommentEntityToCommentDTO(comment, true, true)).collect(Collectors.toList());
    }


}


