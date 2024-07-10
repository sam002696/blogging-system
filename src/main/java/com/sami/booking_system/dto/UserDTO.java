package com.sami.booking_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sami.booking_system.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
    private List<PostDTO> posts ;

    public User to() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        if (posts != null) {
            user.setPosts(posts.stream().map(PostDTO::to).collect(Collectors.toList())); // Assuming PostDTO has a to() method to convert to Post entity
        }
        return user;
    }

    public void update(User user) {
        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        if (posts != null) {
            user.setPosts(posts.stream().map(PostDTO::to).collect(Collectors.toList())); // Assuming PostDTO has a to() method to convert to Post entity
        }
    }
}
