package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.MovieDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IMovieService {
    MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) throws IOException;
}
