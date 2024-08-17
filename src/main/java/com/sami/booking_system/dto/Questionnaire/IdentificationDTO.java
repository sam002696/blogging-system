package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class IdentificationDTO {
    private List<FinancialDTO> financial;
    private List<TechnicalDTO> technical;
    private List<ManagerialDTO> managerial;
    private List<NeedPriorityDTO> needPriority;

    @Data
    public static class FinancialDTO {
        private String financialType;
    }

    @Data
    public static class TechnicalDTO {
        private String technicalType;
    }

    @Data
    public static class ManagerialDTO {
        private String managerialType;
    }

    @Data
    public static class NeedPriorityDTO {
        private String priorityType;
    }
}
