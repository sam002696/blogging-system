package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class HouseKeepingDTO {
    private List<HouseKeepingInfoDTO> houseKeepingInfo;

    @Data
    public static class HouseKeepingInfoDTO {
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
