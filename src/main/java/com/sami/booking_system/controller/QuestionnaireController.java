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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static com.sami.booking_system.utils.ResponseBuilder.*;

import java.util.Optional;

import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Questionnaire API")
@RequestMapping("/api/v1/questionnaire")
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



    @GetMapping("/get/{id}")
    @Operation(summary = "Get a questionnaire by ID", responses = {
            @ApiResponse(description = "Successfully retrieved the questionnaire",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionnaireDTO.class))),
            @ApiResponse(description = "Questionnaire not found", responseCode = "404")
    })
    public ResponseEntity<JSONObject> getQuestionnaireById(@PathVariable Integer id) {
        QuestionnaireDTO questionnaireDTO = questionnaireService.getQuestionnaireById(id);
        return ok(success(questionnaireDTO, "Questionnaire retrieved successfully!").getJson());
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Update a questionnaire", responses = {
            @ApiResponse(description = "Successfully updated the questionnaire",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionnaireDTO.class))),
            @ApiResponse(description = "Questionnaire not found", responseCode = "404")
    })
    public ResponseEntity<JSONObject> updateQuestionnaire(@PathVariable Integer id,
                                                          @RequestBody QuestionnaireDTO questionnaireDTO) {
        Optional<QuestionnaireDTO> updatedQuestionnaire = questionnaireService.updateQuestionnaire(id, questionnaireDTO);
        return updatedQuestionnaire.map(value -> ok(success(value, "Questionnaire updated successfully!").getJson()))
                .orElseGet(() -> badRequest().body(error(HttpStatus.NOT_FOUND, "Questionnaire not found with id: " + id).getJson()));
    }


    // delete a questionnaire


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a questionnaire", responses = {
            @ApiResponse(description = "Successfully deleted the questionnaire",
                    responseCode = "200"),
            @ApiResponse(description = "Questionnaire not found", responseCode = "404")
    })
    public ResponseEntity<JSONObject> deleteQuestionnaire(@PathVariable Integer id) {

            questionnaireService.deleteQuestionnaireById(id);
            return ok(success(null, "Questionnaire deleted successfully!").getJson());

    }


}
