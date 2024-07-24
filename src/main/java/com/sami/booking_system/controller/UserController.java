package com.sami.booking_system.controller;


import com.sami.booking_system.dto.Response;
import com.sami.booking_system.dto.UserDTO;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.helpers.CommonDataHelper;
import com.sami.booking_system.responses.PostResponse;
import com.sami.booking_system.responses.UserResponse;
import com.sami.booking_system.service.interfaces.IUserService;
import com.sami.booking_system.utils.PaginatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.sami.booking_system.utils.ResponseBuilder.paginatedSuccess;
import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Users API")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CommonDataHelper commonDataHelper;


//    @GetMapping("/all")
//    @Operation(summary = "Get all users", responses = {
//            @ApiResponse(description = "Successful got all users",
//                    responseCode = "200",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = UserDTO.class)))
//    })
//    public ResponseEntity<Response> getAllUsers() {
//        Response response = userService.getAllUsers();
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    @Operation(summary = "show lists of all user", description = "show lists of all users")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    public ResponseEntity<JSONObject> lists(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                                            @RequestParam(value = "search", defaultValue = "") String search
    ) {

        PaginatedResponse response = new PaginatedResponse();
        Map<String, Object> map = userService.search(page, size, sortBy, search);
        List<User> userList = (List<User>) map.get("lists");
        List<UserResponse> responses = userList.stream().map(UserResponse::select).toList();
        commonDataHelper.getCommonData(page, size, map, response, responses);
        return ok(paginatedSuccess(response).getJson());
    }


    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "Delete a user", responses = {
            @ApiResponse(description = "Successful deleted the user",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)))
    })
    public ResponseEntity<Response> deleteUSer(@PathVariable("userId") String userId) {
        Response response = userService.deleteUser(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-logged-in-profile-info")
    public ResponseEntity<JSONObject> getLoggedInUserProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
//        Response response = userService.getMyInfo(email);
        UserDTO userDTO = userService.getMyInfo(email);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
        return ok(success(userDTO).getJson());
    }

}
