package com.sami.booking_system.entity.LightEngineeringQuestionnaire;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "light_engineering_questions")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String conductedBy;

    @Embedded
    private GeneralInfo generalInfo;

    @Embedded
    private Organization organization;

    @Embedded
    private RawMaterial rawMaterial;

    @Embedded
    private Manufacturing manufacturing;

    @Embedded
    private MachineUsed machineUsed;

    @Embedded
    private SafetyMeasures safetyMeasures;

    @Embedded
    private SafetyRelations safetyRelations;

    @Embedded
    private HouseKeeping houseKeeping;

    @Embedded
    private ExistingMachinerySafety existingMachinerySafety;

    @Embedded
    private ProductOutput productOutput;

    @Embedded
    private Identification identification;
}
