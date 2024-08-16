package com.sami.booking_system.entity.LightEngineeringQuestionnaire;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class Manufacturing {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<ManufacturingProcessRequired> manufacturingProcessRequired;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<MaintenanceAvailability> maintenanceAvailability;

    private int noOfUnSkilledEmployees;
    private int noOfSkilledEmployees;
    private int noOfSemiSkilledEmployees;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<SkilledLevel> skilledLevel;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<SemiSkilledLevel> semiSkilledLevel;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<UnskilledLevel> unskilledLevel;

    private String safetyKnowledgeLevel;
    private String operationSpace;
    private String storeSpace;

    @Data
    @Embeddable
    public static class ManufacturingProcessRequired {
        private String processType;
    }

    @Data
    @Embeddable
    public static class MaintenanceAvailability {
        private String type;
    }

    @Data
    @Embeddable
    public static class SkilledLevel {
        private String levelYear;
    }

    @Data
    @Embeddable
    public static class SemiSkilledLevel {
        private String levelYear;
    }

    @Data
    @Embeddable
    public static class UnskilledLevel {
        private String levelYear;
    }
}
