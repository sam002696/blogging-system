package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class ProductOutputDTO {
    private String prHourly;
    private String prDaily;
    private String prWeekly;
    private String prMonthly;
    private String prOrderBased;
    private int prDefectRate;
    private int prReworkRate;
    private List<DefectSourceDTO> defectSource;
    private String qdDaily;
    private String qdWeekly;
    private String qdMonthly;
    private String qdOrderBased;

    @Data
    public static class DefectSourceDTO {
        private String source;
    }
}
