package com.sami.booking_system.entity.LightEngineeringQuestionnaire;



import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public class ExistingMachinerySafety {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<ExistingMachinerySafetyInfo> existingMachinerySafetyInfo;

    @Data
    @Embeddable
    public static class ExistingMachinerySafetyInfo {
        private String machineType;
        private String machineSafeguard;
        private String machineSafetyEquipment;
        private String machineTrainingEmployee;
        private String machineMaintenanceSchedule;
        private String machineReplacingMc;
        private String machineAvailableToolbox;
    }
}
