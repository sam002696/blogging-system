package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class ManufacturingDTO {
    private List<ProcessTypeDTO> manufacturingProcessRequired;
    private List<MaintenanceAvailabilityDTO> maintenanceAvailability;
    private int noOfUnSkilledEmployees;
    private int noOfSkilledEmployees;
    private int noOfSemiSkilledEmployees;
    private List<SkilledLevelDTO> skilledLevel;
    private List<SemiSkilledLevelDTO> semiSkilledLevel;
    private List<UnskilledLevelDTO> unskilledLevel;
    private String safetyKnowledgeLevel;
    private String operationSpace;
    private String storeSpace;

    @Data
    public static class ProcessTypeDTO {
        private String processType;
    }

    @Data
    public static class MaintenanceAvailabilityDTO {
        private String type;
    }

    @Data
    public static class SkilledLevelDTO {
        private String levelYear;
    }

    @Data
    public static class SemiSkilledLevelDTO {
        private String levelYear;
    }

    @Data
    public static class UnskilledLevelDTO {
        private String levelYear;
    }
}
