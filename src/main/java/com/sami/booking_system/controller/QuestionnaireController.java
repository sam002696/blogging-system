package com.sami.booking_system.controller;

import com.sami.booking_system.dto.Questionnaire.QuestionnaireDTO;
import com.sami.booking_system.service.impl.QuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Questionnaire API")
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping("/add")
    @Operation(summary = "Add a questionnaire", responses = {
            @ApiResponse(description = "Successfully added the questionnaire",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionnaireDTO.class)))
    })
    public ResponseEntity<JSONObject> addNewQuestionnaire(@RequestBody QuestionnaireDTO questionnaireDTO) {
        QuestionnaireDTO savedQuestionnaire = questionnaireService.addNewQuestionnaire(questionnaireDTO);


        return ok(success(savedQuestionnaire, "Questionnaire added successfully!").getJson());
    }
}
