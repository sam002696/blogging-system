package com.sami.booking_system.entity.LightEngineeringQuestionnaire;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Organization {
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
