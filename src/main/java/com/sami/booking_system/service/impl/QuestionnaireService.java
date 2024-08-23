package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.Questionnaire.QuestionnaireDTO;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.LightEngineeringQuestionnaire.Questionnaire;
import com.sami.booking_system.exceptions.CustomMessageException;
import com.sami.booking_system.mapper.QuestionnaireMapper;
import com.sami.booking_system.repository.QuestionnaireRepository;
import com.sami.booking_system.service.interfaces.IQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
    public QuestionnaireDTO getQuestionnaireById(Integer questionId) {
        Questionnaire questionnaire = questionnaireRepository.findById(questionId)
                .orElseThrow(() -> new CustomMessageException("Questionnaire not found!"));
        return questionnaireMapper.toDTO(questionnaire);
    }

    @Override
    public Optional<QuestionnaireDTO> updateQuestionnaire(Integer id, QuestionnaireDTO questionnaireDTO) {
        return questionnaireRepository.findById(id).map(existingQuestionnaire -> {
            // Map updated fields from DTO to the existing entity
            questionnaireMapper.toEntity(questionnaireDTO, existingQuestionnaire);
            Questionnaire updatedQuestionnaire = questionnaireRepository.save(existingQuestionnaire);
            return Optional.of(questionnaireMapper.toDTO(updatedQuestionnaire));
        }).orElse(Optional.empty());
    }


    @Override
    public Response deleteQuestionnaire(Long CommentId) {
        return null;
    }

    @Override
    public void deleteQuestionnaireById(Integer id) {
        Questionnaire questionnaire = questionnaireRepository.findById(id)
                .orElseThrow(() -> new CustomMessageException("Questionnaire not found with" + id));
        questionnaireRepository.delete(questionnaire);
    }
}
