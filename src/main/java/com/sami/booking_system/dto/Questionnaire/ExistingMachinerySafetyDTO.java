package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class ExistingMachinerySafetyDTO {
    private List<ExistingMachinerySafetyInfoDTO> existingMachinerySafetyInfo;

    @Data
    public static class ExistingMachinerySafetyInfoDTO {
        private String machineType;
        private String machineSafeguard;
        private String machineSafetyEquipment;
        private String machineTrainingEmployee;
        private String machineMaintenanceSchedule;
        private String machineReplacingMc;
        private String machineAvailableToolbox;
    }
}
