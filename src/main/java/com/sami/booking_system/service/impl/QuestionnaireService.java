package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.Questionnaire.QuestionnaireDTO;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.LightEngineeringQuestionnaire.Questionnaire;
import com.sami.booking_system.mapper.QuestionnaireMapper;
import com.sami.booking_system.repository.QuestionnaireRepository;
import com.sami.booking_system.service.interfaces.IQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class QuestionnaireService implements IQuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @Override
    public QuestionnaireDTO addNewQuestionnaire(QuestionnaireDTO questionnaireDTO) {
        Questionnaire questionnaire = questionnaireMapper.toEntity(questionnaireDTO);
        Questionnaire savedQuestionnaire = questionnaireRepository.save(questionnaire);
        return questionnaireMapper.toDTO(savedQuestionnaire);
    }


    @Override
    public Response deleteQuestionnaire(Long CommentId) {
        return null;
    }
}
