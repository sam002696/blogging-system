package com.sami.booking_system.service.interfaces;


import com.sami.booking_system.dto.Questionnaire.QuestionnaireDTO;
import com.sami.booking_system.dto.Response;

import java.util.Optional;

public interface IQuestionnaireService {

    QuestionnaireDTO addNewQuestionnaire(QuestionnaireDTO questionnaireDTO);

    QuestionnaireDTO getQuestionnaireById(Integer id);

    Optional<QuestionnaireDTO> updateQuestionnaire(Integer id, QuestionnaireDTO questionnaireDTO);

    Response deleteQuestionnaire(Long CommentId);

    void deleteQuestionnaireById(Integer id);

    // pdf download
    byte[] generateReport(Integer id);
}
