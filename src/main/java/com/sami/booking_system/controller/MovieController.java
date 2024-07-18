package com.sami.booking_system.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sami.booking_system.dto.CommentRequest;
import com.sami.booking_system.dto.MovieDTO;
import com.sami.booking_system.exceptions.CustomMessageException;
import com.sami.booking_system.service.impl.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Movie API")
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    MovieService movieService;


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Add a movie", responses = {
            @ApiResponse(description = "Successfully added the movie",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)))
    })
    public ResponseEntity<JSONObject> addMovieHandler(@RequestPart MultipartFile file,
                                                      @RequestPart String movieDto) throws IOException {

        if (file.isEmpty()) {
            throw new CustomMessageException("File is empty! Please send another file!");
        }
        MovieDTO dto = convertToMovieDto(movieDto);
        MovieDTO movieDTO = movieService.addMovie(dto, file);


        return ok(success(movieDTO, "Movie added successfully").getJson());

    }


    private MovieDTO convertToMovieDto(String movieDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoObj, MovieDTO.class);
    }
}
