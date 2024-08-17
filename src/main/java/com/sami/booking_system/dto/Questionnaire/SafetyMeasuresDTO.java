package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class SafetyMeasuresDTO {
    private String stateOfElectricWiring;
    private List<FireFightingGearDTO> fireFightingGears;
    private String firstAidBox;
    private String nearestMedicalAid;
    private List<SafetyMeasuresAvailableDTO> safetyMeasuresAvailable;

    @Data
    public static class FireFightingGearDTO {
        private String gearType;
    }

    @Data
    public static class SafetyMeasuresAvailableDTO {
        private String machineType;
        private String machineGuardingSystem;
        private String machineEmergencyStop;
        private String machineColorUsedSafetySigns;
        private String machineLubricationSystem;
        private String machineInterLockingSystem;
        private String machineElectricalProtection;
        private String machineOthers;
    }
}
