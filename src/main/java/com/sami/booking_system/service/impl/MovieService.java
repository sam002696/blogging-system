package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.MovieDTO;
import com.sami.booking_system.entity.Movie;
import com.sami.booking_system.repository.MovieRepository;
import com.sami.booking_system.responses.FileUploadResponse;
import com.sami.booking_system.service.CloudinaryService;
import com.sami.booking_system.service.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MovieService implements IMovieService {

    @Autowired
    MovieRepository movieRepository;
    
    @Autowired
    CloudinaryService cloudinaryService;
    
   


    @Override
    public MovieDTO addMovie(MovieDTO movieDto, MultipartFile file) throws IOException {
        // 1. upload the file to Cloudinary
        FileUploadResponse uploadResponse = cloudinaryService.uploadFile(file);

        // 2. set the value of field 'poster' as filename
        movieDto.setPoster(uploadResponse.getFileName());
        movieDto.setPosterUrl(uploadResponse.getUrl());

        // 3. map dto to Movie object
        Movie movie = new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster(),
                movieDto.getPosterUrl()
        );

        // 4. save the movie object -> saved Movie object
        Movie savedMovie = movieRepository.save(movie);

        // 5. generate the posterUrl
//        String posterUrl = uploadedFileUrl; // Since Cloudinary returns the URL directly

        // 6. map Movie object to DTO object and return it
        MovieDTO response = new MovieDTO(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                savedMovie.getPosterUrl()
        );

        return response;
    }
}
