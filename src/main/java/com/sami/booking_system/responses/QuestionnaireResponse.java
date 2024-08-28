package com.sami.booking_system.responses;

import com.sami.booking_system.entity.LightEngineeringQuestionnaire.Questionnaire;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class QuestionnaireResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String conductedBy;
    private String industryName;
    private String location;
    private String address;

    public static QuestionnaireResponse select(Questionnaire questionnaire) {
        if (questionnaire == null) {
            return null;
        }
        QuestionnaireResponse response = new QuestionnaireResponse();
        response.setId(questionnaire.getId());
        response.setTitle(questionnaire.getTitle());
        response.setConductedBy(questionnaire.getConductedBy());

        if (questionnaire.getGeneralInfo() != null) {
            response.setIndustryName(questionnaire.getGeneralInfo().getIndustryName());
            response.setLocation(questionnaire.getGeneralInfo().getLocation());
            response.setAddress(questionnaire.getGeneralInfo().getAddress());
        }

        return response;
    }

}
