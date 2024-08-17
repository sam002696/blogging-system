package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class GeneralInfoDTO {
    private String industryName;
    private String location;
    private String address;
    private int yearEstablished;
    private String tel;
    private String fax;
    private String proprietorName;
    private String educationalBackground;
    private String trainingAchieved;
    private String productType;
    private String productDemand;
    private String unitPrice;
    private List<ProductDecisionDTO> productDecision;

    @Data
    public static class ProductDecisionDTO {
        private String decision;
    }
}
