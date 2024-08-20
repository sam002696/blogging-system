package com.sami.booking_system.mapper;


import com.sami.booking_system.dto.Questionnaire.QuestionnaireDTO;
import com.sami.booking_system.dto.Questionnaire.SafetyRelationsDTO;
import com.sami.booking_system.entity.LightEngineeringQuestionnaire.Questionnaire;
import com.sami.booking_system.entity.LightEngineeringQuestionnaire.SafetyRelations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionnaireMapper {

    @Mapping(target = "id", ignore = true) // Ignoring ID as it is auto-generated
    Questionnaire toEntity(QuestionnaireDTO questionnaireDTO);

    QuestionnaireDTO toDTO(Questionnaire questionnaire);

    @Mapping(target = "id", ignore = true) // Ignoring ID during update
    void toEntity(QuestionnaireDTO questionnaireDTO, @MappingTarget Questionnaire questionnaire);

}
