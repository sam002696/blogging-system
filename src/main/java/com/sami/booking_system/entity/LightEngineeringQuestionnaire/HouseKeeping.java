package com.sami.booking_system.entity.LightEngineeringQuestionnaire;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public class HouseKeeping {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<HouseKeepingInfo> houseKeepingInfo;

    @Data
    @Embeddable
    public static class HouseKeepingInfo {
        private String practiceType;
        private String cleaningOftenDone;
        private String cleaningEmployee;
        private String toolsEquipment;
        private String frequentlyNeededTool;
        private String panelsNeededTool;
        private String gears;
        private String spread;
        private String spill;
    }
}
