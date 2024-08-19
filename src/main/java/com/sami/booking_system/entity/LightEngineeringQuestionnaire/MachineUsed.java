package com.sami.booking_system.entity.LightEngineeringQuestionnaire;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public  class MachineUsed {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<MachineUsedInfo> machineUsedInfo;

    @Data
    @Embeddable
    public static class MachineUsedInfo {
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

