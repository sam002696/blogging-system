package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class SafetyRelationsDTO {
    private List<SafetyRelatedInfoDTO> safetyRelatedInfo;
    private String exerciseSafetyMeasuresAvailability;
    private String safetyMeasureAcknowledgement;
    private String safeOperationsTraining;
    private String employeeSafeguardKnowledge;
    private String safeGuardsRemovalAcknowledgement;
    private String safeGuardUnavailabilityAcknowledgement;

    @Data
    public static class SafetyRelatedInfoDTO {
        private String machineType;
        private int machineAccidentRate;
        private String machineInjuryType;
        private String machineAbsenteeism;
        private String machineExpenses;
        private String machineTurnOverRate;
    }
}
