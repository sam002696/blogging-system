package com.sami.booking_system.service.interfaces;


import com.sami.booking_system.dto.Questionnaire.QuestionnaireDTO;
import com.sami.booking_system.dto.Response;

import java.util.Map;
import java.util.Optional;

public interface IQuestionnaireService {

    QuestionnaireDTO addNewQuestionnaire(QuestionnaireDTO questionnaireDTO);

    QuestionnaireDTO getQuestionnaireById(Integer id);

    Optional<QuestionnaireDTO> updateQuestionnaire(Integer id, QuestionnaireDTO questionnaireDTO);

    Response deleteQuestionnaire(Long CommentId);

    void deleteQuestionnaireById(Integer id);

    Map<String, Object> search(Integer page, Integer size, String sortBy, String search);

    // pdf download
    byte[] generateReport(Integer id);
}
