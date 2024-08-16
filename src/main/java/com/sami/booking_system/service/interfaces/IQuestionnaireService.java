package com.sami.booking_system.service.interfaces;


import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.LightEngineeringQuestionnaire.Questionnaire;

public interface IQuestionnaireService {

    Questionnaire addNewQuestionnaire(String response);

    Response deleteQuestionnaire(Long CommentId);
}
