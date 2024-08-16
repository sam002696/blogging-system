package com.sami.booking_system.entity.LightEngineeringQuestionnaire;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public class ProductOutput {
    private String prHourly;
    private String prDaily;
    private String prWeekly;
    private String prMonthly;
    private String prOrderBased;
    private int prDefectRate;
    private int prReworkRate;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<DefectSource> defectSource;

    private String qdDaily;
    private String qdWeekly;
    private String qdMonthly;
    private String qdOrderBased;

    @Data
    @Embeddable
    public static class DefectSource {
        private String source;
    }
}
