package com.sami.booking_system.entity.LightEngineeringQuestionnaire;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class GeneralInfo {
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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductDecision> productDecision;

    @Data
    @Embeddable
    public static class ProductDecision {
        private String decision;
    }
}
