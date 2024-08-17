package com.sami.booking_system.service.interfaces;


import com.sami.booking_system.dto.Questionnaire.QuestionnaireDTO;
import com.sami.booking_system.dto.Response;

public interface IQuestionnaireService {

    QuestionnaireDTO addNewQuestionnaire(QuestionnaireDTO questionnaireDTO);

    Response deleteQuestionnaire(Long CommentId);
}
