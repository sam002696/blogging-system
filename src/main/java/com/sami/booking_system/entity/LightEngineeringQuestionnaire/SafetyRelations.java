package com.sami.booking_system.entity.LightEngineeringQuestionnaire;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public  class SafetyRelations {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<SafetyRelatedInfo> safetyRelatedInfo;

    private String exerciseSafetyMeasuresAvailability;
    private String safetyMeasureAcknowledgement;
    private String safeOperationsTraining;
    private String employeeSafeguardKnowledge;
    private String safeGuardsRemovalAcknowledgement;
    private String safeGuardUnavailabilityAcknowledgement;

    @Data
    @Embeddable
    public static class SafetyRelatedInfo {
        private String machineType;
        private int machineAccidentRate;
        private String machineInjuryType;
        private String machineAbsenteeism;
        private String machineExpenses;
        private String machineTurnOverRate;
    }
}
