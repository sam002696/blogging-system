package com.sami.booking_system.validators;

import com.sami.booking_system.dto.PostDTO;
import com.sami.booking_system.exceptions.CustomMessageException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PostValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return PostDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostDTO dto = (PostDTO) target;

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new CustomMessageException("Title is required");
        }

        if (dto.getContent() == null || dto.getContent().isBlank()) {
            throw new CustomMessageException("Content is required");
        }

        // Additional custom validations can be added here
    }
}

