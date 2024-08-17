package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

@Data
public class OrganizationDTO {
    private String fixedCapital;
    private String machinery;
    private String land;
    private String building;
    private String workerWages;
    private String others;
    private String financeSource;
    private int fullTimeEmployees;
    private int partTimeEmployees;
}
