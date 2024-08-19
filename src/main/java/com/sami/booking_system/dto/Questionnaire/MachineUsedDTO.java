package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class MachineUsedDTO {
    private List<MachineUsedInfoDTO> machineUsedInfo;

    @Data
    public static class MachineUsedInfoDTO {
        private String machineType;
        private int machineQuantity;
        private String machineCountry;
        private String machineAccuracy;
        private String machineUsageTime;
        private String machineAgeType;
        private String machineYearUsage;
        private String machineOthers;
    }
}
