package com.sami.booking_system.entity.LightEngineeringQuestionnaire;



import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public class SafetyMeasures {
    private String stateOfElectricWiring;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<FireFightingGears> fireFightingGears;

    private String firstAidBox;
    private String nearestMedicalAid;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<SafetyMeasuresAvailable> safetyMeasuresAvailable;

    @Data
    @Embeddable
    public static class FireFightingGears {
        private String gearType;
    }

    @Data
    @Embeddable
    public static class SafetyMeasuresAvailable {
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
